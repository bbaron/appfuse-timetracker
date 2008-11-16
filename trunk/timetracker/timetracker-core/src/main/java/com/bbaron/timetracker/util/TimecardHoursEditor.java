package com.bbaron.timetracker.util;

import com.bbaron.timetracker.model.TimecardHours;

public class TimecardHoursEditor extends IntegralValueEditor {

    public TimecardHoursEditor() {
        super();
    }

    public TimecardHoursEditor(boolean allowEmpty) {
        super(allowEmpty);
    }

    @Override
    protected Object construct(int n) {
        return new TimecardHours(n);
    }

}
