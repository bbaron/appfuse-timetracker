package com.bbaron.timetracker.web.commands;

import com.bbaron.timetracker.model.TimecardDate;


public class NewTimecard {

    private Long submitterId;
    private TimecardDate startDate = TimecardDate.today();

    public Long getSubmitterId() {
        return submitterId;
    }

    public void setSubmitterId(Long submitterId) {
        this.submitterId = submitterId;
    }

    public TimecardDate getStartDate() {
        return startDate;
    }
    
    public void setStartDate(TimecardDate startDate) {
        this.startDate = startDate;
    }

}
