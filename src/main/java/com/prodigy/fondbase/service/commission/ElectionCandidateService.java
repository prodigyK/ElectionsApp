package com.prodigy.fondbase.service.commission;


import com.prodigy.fondbase.model.commission.ElectionCandidate;

import java.io.File;
import java.util.List;

public interface ElectionCandidateService {
    ElectionCandidate save(ElectionCandidate candidate);

    List<ElectionCandidate> getAll();

    ElectionCandidate get(int id);

    void enable(int id, boolean enabled);

    void leftOut(int id, boolean enabled);

    void ourCand(int id, boolean enabled);

    void ourTechCand(int id, boolean enabled);

    List<ElectionCandidate> getTechCandidates(int id);

    boolean saveTechnicals(ElectionCandidate candidate, List<ElectionCandidate> techs);

    boolean uploadCandidates(File file, int electionId);
}
