package com.bbaron.timetracker.model;

import org.joda.time.Minutes;

public class TimecardMinutes extends IntegralValue<Minutes> {

    public TimecardMinutes(Minutes minutes) {
        super(minutes, 0, 60);
    }
    
    public TimecardMinutes(int minutes) {
        this(Minutes.minutes(minutes));
    }
    
    @Override
    protected int nullSafeToInteger(Minutes value) {
        return value.getMinutes();
    }    
    
}
