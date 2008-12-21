package com.bbaron.timetracker.util;

import com.bbaron.timetracker.temporal.TimecardHours;

public class TimecardHoursEditorTest extends AbstractIntegralValueEditorTest {

    @Override
    protected IntegralValueEditor createEditor(boolean allowEmpty) {
        return new TimecardHoursEditor(allowEmpty);
    }

    @Override
    protected Object createValue(int n) {
        return TimecardHours.hours(n);
    }

}
