package com.prodigy.fondbase.dao.commission;

import com.prodigy.fondbase.model.commission.ElectionCandidate;

import java.util.List;

public interface ElectionCandidateDao {

    ElectionCandidate save(ElectionCandidate candidate);

    List<ElectionCandidate> getAll();

    ElectionCandidate get(int id);

    List<ElectionCandidate> getTechCandidates(int id);

    boolean deleteAllByElection(int electionId);

}
