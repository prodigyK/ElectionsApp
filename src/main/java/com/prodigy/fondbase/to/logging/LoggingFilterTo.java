package com.prodigy.fondbase.to.logging;

import java.time.LocalDate;

public class LoggingFilterTo {

    private Integer userId;

    private LocalDate dateFrom;

    private LocalDate dateTo;

    public LoggingFilterTo() {
    }

    public LoggingFilterTo(Integer userId, LocalDate dateFrom, LocalDate dateTo) {
        this.userId = userId;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public LocalDate getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(LocalDate dateFrom) {
        this.dateFrom = dateFrom;
    }

    public LocalDate getDateTo() {
        return dateTo;
    }

    public void setDateTo(LocalDate dateTo) {
        this.dateTo = dateTo;
    }
}
