package com.prodigy.fondbase.service.commission;


import com.prodigy.fondbase.dao.commission.ElectionCandidateDao;
import com.prodigy.fondbase.dao.commission.ElectionDao;
import com.prodigy.fondbase.model.commission.Election;
import com.prodigy.fondbase.model.commission.ElectionCandidate;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.FileInputStream;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Service
@Transactional(readOnly = true)
public class ElectionCandidateServiceImpl implements ElectionCandidateService {

    @Autowired
    private ElectionCandidateDao dao;

    @Autowired
    private ElectionDao electionDao;

    @Override
    @Transactional
    public ElectionCandidate save(ElectionCandidate candidate) {
        return dao.save(candidate);
    }

    @Override
    public List<ElectionCandidate> getAll() {
        return dao.getAll();
    }

    @Override
    public ElectionCandidate get(int id) {
        return dao.get(id);
    }

    @Override
    @Transactional
    public void enable(int id, boolean enabled) {
        ElectionCandidate candidate = dao.get(id);
        candidate.setEnabled(enabled);
    }

    @Override
    @Transactional
    public void leftOut(int id, boolean enabled) {
        ElectionCandidate candidate = dao.get(id);
        candidate.setLeftOut(enabled);
    }

    @Override
    @Transactional
    public void ourCand(int id, boolean enabled) {
        ElectionCandidate candidate = dao.get(id);
        candidate.setOurCand(enabled);
    }

    @Override
    @Transactional
    public void ourTechCand(int id, boolean enabled) {
        ElectionCandidate candidate = dao.get(id);
        candidate.setOurTechCand(enabled);
    }

    @Override
    public List<ElectionCandidate> getTechCandidates(int id) {
        return dao.getTechCandidates(id);
    }

    @Override
    @Transactional
    public boolean saveTechnicals(ElectionCandidate candidate, List<ElectionCandidate> techs) {

        List<ElectionCandidate> techCandList = getTechCandidates(candidate.getId());
        for (ElectionCandidate cand : techCandList) {
            cand.setOurTechCand(false);
            cand.setTechOfParent(null);
        }

        for (ElectionCandidate cand : techs) {
            cand.setTechOfParent(candidate);
            dao.save(cand);
        }

        return true;
    }

    @Override
    @Transactional
    public boolean uploadCandidates(File file, int electionId) {
        Map<String, String> candidates = new TreeMap<>();

        try (FileInputStream fileInputStream = new FileInputStream(file)) {

            XSSFWorkbook workBook = new XSSFWorkbook(fileInputStream);
            XSSFSheet sheet = workBook.getSheetAt(0);
            Iterator<Row> itRow = sheet.iterator();


            Row row; // = itRow.next();

            while (itRow.hasNext()) {

                row = itRow.next();

                Cell cell;
                cell = row.getCell(0);
                if (cell == null)
                    continue;
                cell.setCellType(CellType.STRING);
                String xlsCandidateOrder = cell.getStringCellValue();

                cell = row.getCell(1);
                if (cell == null)
                    continue;
                cell.setCellType(CellType.STRING);
                String xlsCandidateName = cell.getStringCellValue();

                candidates.putIfAbsent(xlsCandidateName, xlsCandidateOrder);
            }

        } catch (Exception e){
            return false;
        }

        dao.deleteAllByElection(electionId);

        for(Map.Entry candidate : candidates.entrySet()){
            String name = (String) candidate.getKey();
            String order = (String) candidate.getValue();

            ElectionCandidate cand = new ElectionCandidate(name, Integer.parseInt(order));
            cand.setElection(electionDao.get(electionId));

            dao.save(cand);
        }

        return true;
    }
}
