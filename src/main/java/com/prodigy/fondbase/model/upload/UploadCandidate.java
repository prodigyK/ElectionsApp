package com.prodigy.fondbase.model.upload;

import org.springframework.web.multipart.MultipartFile;

public class UploadCandidate {

    private Integer election;

    private MultipartFile file;

    public UploadCandidate() {
    }

    public UploadCandidate(MultipartFile file) {
        this.file = file;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    public Integer getElection() {
        return election;
    }

    public void setElection(Integer election) {
        this.election = election;
    }
}
