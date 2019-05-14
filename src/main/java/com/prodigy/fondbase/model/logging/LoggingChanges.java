package com.prodigy.fondbase.model.logging;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.prodigy.fondbase.model.AbstractBaseEntity;

import javax.persistence.*;

@Entity
@Table(name = "bf_log_changes")
public class LoggingChanges extends AbstractBaseEntity{

    @Column(name = "field_name")
    private String fieldName;

    @Column(name = "previousValue")
    private String previousValue;

    @Column(name = "newValue")
    private String newValue;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "log_main_id")
    private LoggingMain main;

    public LoggingChanges() {
    }

    public LoggingChanges(String fieldName, String previousValue, String newValue) {
        this.fieldName = fieldName;
        this.previousValue = previousValue;
        this.newValue = newValue;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getPreviousValue() {
        return previousValue;
    }

    public void setPreviousValue(String previousValue) {
        this.previousValue = previousValue;
    }

    public String getNewValue() {
        return newValue;
    }

    public void setNewValue(String newValue) {
        this.newValue = newValue;
    }

    public LoggingMain getMain() {
        return main;
    }

    public void setMain(LoggingMain main) {
        this.main = main;
    }
}
