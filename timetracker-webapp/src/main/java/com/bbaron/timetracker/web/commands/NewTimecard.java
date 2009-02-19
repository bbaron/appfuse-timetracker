package com.bbaron.timetracker.web.commands;

import com.bbaron.timetracker.temporal.TimecardDate;


public class NewTimecard {

    private String submitter;
    private TimecardDate startDate = TimecardDate.today();

    public String getSubmitter() {
        return submitter;
    }

    public void setSubmitter(String submitterId) {
        this.submitter = submitterId;
    }

    public TimecardDate getStartDate() {
        return startDate;
    }
    
    public void setStartDate(TimecardDate startDate) {
        this.startDate = startDate;
    }

}
