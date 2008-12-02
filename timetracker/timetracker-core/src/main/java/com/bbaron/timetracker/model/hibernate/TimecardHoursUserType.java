package com.bbaron.timetracker.model.hibernate;

import com.bbaron.timetracker.model.TimecardHours;

public final class TimecardHoursUserType extends IntegralValueUserType<TimecardHours> {

    public TimecardHoursUserType() {
        super(TimecardHours.class);
    }

    @Override
    protected TimecardHours construct(int value) {
        return TimecardHours.hours(value);
    }

}
