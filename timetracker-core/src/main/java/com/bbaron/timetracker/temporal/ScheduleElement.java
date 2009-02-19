package com.bbaron.timetracker.temporal;

import java.util.Date;

public class ScheduleElement implements Occurable {

    private final String event;
    private final TemporalExpression temporalExpression;

    public ScheduleElement(String event, TemporalExpression temporalExpression) {
        this.event = event;
        this.temporalExpression = temporalExpression;
    }

    @Override
    public boolean isOccurring(String eventArg, Date date) {
        if (this.event == eventArg)
            return this.temporalExpression.includes(date);
         else
            return false;
    }

}
