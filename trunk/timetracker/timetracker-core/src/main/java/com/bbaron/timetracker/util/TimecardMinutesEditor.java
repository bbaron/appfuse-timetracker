package com.bbaron.timetracker.util;

import com.bbaron.timetracker.model.TimecardMinutes;

public class TimecardMinutesEditor extends IntegralValueEditor {

    public TimecardMinutesEditor() {
        super();
    }

    public TimecardMinutesEditor(boolean allowEmpty) {
        super(allowEmpty);
    }

    @Override
    protected Object construct(int n) {
        return new TimecardMinutes(n);
    }

}
