package com.prodigy.fondbase.service.commission;

import com.prodigy.fondbase.dao.commission.ElectionDao;
import com.prodigy.fondbase.model.commission.Election;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class ElectionServiceImpl implements ElectionService {

    @Autowired
    private ElectionDao electionDao;

    @Override
    @Transactional
    public Election save(Election election) {
        return electionDao.save(election);
    }

    @Override
    public Election get(int id) {
        return electionDao.get(id);
    }

    @Override
    public List<Election> getAll() {
        return electionDao.getAll();
    }

    @Override
    @Transactional
    public void enable(int id, boolean enabled) {
        Election election = electionDao.get(id);
        election.setEnabled(enabled);
    }
}
