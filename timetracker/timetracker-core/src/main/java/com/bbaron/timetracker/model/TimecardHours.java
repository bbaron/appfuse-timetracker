package com.bbaron.timetracker.model;

import org.joda.time.Hours;

public class TimecardHours extends IntegralValue<Hours> {

    public TimecardHours(Hours hours) {
        super(hours, 0, 24);
    }
    
    public TimecardHours(int hours) {
        this(Hours.hours(hours));
    }
    
    @Override
    protected int nullSafeToInteger(Hours hours) {
        return hours.getHours();
    }    
    
}
