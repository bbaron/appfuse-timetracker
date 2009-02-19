package com.bbaron.timetracker.model.hibernate;

import com.bbaron.timetracker.temporal.TimecardMinutes;

public final class TimecardMinutesUserType extends IntegralValueUserType<TimecardMinutes> {

    public TimecardMinutesUserType() {
        super(TimecardMinutes.class);
    }

    @Override
    protected TimecardMinutes construct(int value) {
        return TimecardMinutes.minutes(value);
    }

}
