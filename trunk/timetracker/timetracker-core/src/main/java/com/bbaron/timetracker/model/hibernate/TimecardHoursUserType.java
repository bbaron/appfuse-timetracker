package com.bbaron.timetracker.model.hibernate;

import com.bbaron.timetracker.model.TimecardHours;

public class TimecardHoursUserType extends IntegralValueUserType<TimecardHours> {

    public TimecardHoursUserType() {
        super(TimecardHours.class);
    }

    @Override
    protected TimecardHours construct(int value) {
        return new TimecardHours(value);
    }

}
