package com.bbaron.timetracker.web.commands;

import org.joda.time.LocalDate;

public class NewTimecard {

    private Long submitterId;
    private LocalDate startDate = new LocalDate();

    public Long getSubmitterId() {
        return submitterId;
    }

    public void setSubmitterId(Long submitterId) {
        this.submitterId = submitterId;
    }

    public LocalDate getStartDate() {
        return startDate;
    }
    
    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

}
