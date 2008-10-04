package com.bbaron.timetracker.web.commands;

import java.util.Date;

public class NewTimecard {

    private Long submitterId;
    private Date startDate = new Date();

    public Long getSubmitterId() {
        return submitterId;
    }

    public void setSubmitterId(Long submitterId) {
        this.submitterId = submitterId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

}
