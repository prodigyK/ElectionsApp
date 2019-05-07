package com.prodigy.fondbase.to;


import com.prodigy.fondbase.model.commission.ElectionCandidate;

import java.util.ArrayList;
import java.util.List;

public class ElectionCandidateTo extends BaseTo {

    private List<ElectionCandidate> techCandidates = new ArrayList<>();

    public ElectionCandidateTo() {
    }

    public ElectionCandidateTo(Integer id, List<ElectionCandidate> techCandidatesId) {
        super(id);
        this.techCandidates = techCandidatesId;
    }

    public List<ElectionCandidate> getTechCandidates() {
        return techCandidates;
    }

    public void setTechCandidates(List<ElectionCandidate> techCandidates) {
        this.techCandidates = techCandidates;
    }
}
