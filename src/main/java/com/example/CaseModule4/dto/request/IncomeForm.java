package com.example.CaseModule4.dto.request;

import java.util.Date;

public class IncomeForm {
    Date fromDate;
    Date toDate;

    public IncomeForm() {
    }

    public IncomeForm(Date fromDate, Date toDate) {
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }
}
