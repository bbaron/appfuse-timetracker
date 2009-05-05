package com.bbaron.timetracker.util;

import com.bbaron.timetracker.temporal.TimecardMinutes;

public class TimecardMinutesEditor extends IntegralValueEditor {

    public TimecardMinutesEditor() {
        super();
    }

    public TimecardMinutesEditor(boolean allowEmpty) {
        super(allowEmpty);
    }

    @Override
    protected Object construct(int n) {
        return TimecardMinutes.minutes(n);
    }

}
