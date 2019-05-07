package com.prodigy.fondbase.download;

import com.prodigy.fondbase.model.Subscriber;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class AnalyzeDataResult {

    List<Subscriber> resultList = new ArrayList<>();

    Set<String> streetsNotFound = new TreeSet<>();

    public AnalyzeDataResult() {
    }

    public List<Subscriber> getResultList() {
        return resultList;
    }

    public void setResultList(List<Subscriber> resultList) {
        this.resultList = resultList;
    }

    public Set<String> getStreetsNotFound() {
        return streetsNotFound;
    }

    public void setStreetsNotFound(Set<String> streetsNotFound) {
        this.streetsNotFound = streetsNotFound;
    }
}
