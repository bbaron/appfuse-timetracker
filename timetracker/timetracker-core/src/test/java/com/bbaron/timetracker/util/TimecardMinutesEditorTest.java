package com.bbaron.timetracker.util;

import com.bbaron.timetracker.temporal.TimecardMinutes;

public class TimecardMinutesEditorTest extends AbstractIntegralValueEditorTest {

    @Override
    protected IntegralValueEditor createEditor(boolean allowEmpty) {
        return new TimecardMinutesEditor(allowEmpty);
    }

    @Override
    protected Object createValue(int n) {
        return TimecardMinutes.minutes(n);
    }

}
