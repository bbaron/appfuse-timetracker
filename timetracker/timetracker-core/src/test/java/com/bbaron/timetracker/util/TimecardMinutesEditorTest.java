package com.bbaron.timetracker.util;

import com.bbaron.timetracker.model.TimecardMinutes;

public class TimecardMinutesEditorTest extends AbstractIntegralValueEditorTest {

    @Override
    protected IntegralValueEditor createEditor(boolean allowEmpty) {
        return new TimecardMinutesEditor(allowEmpty);
    }

    @Override
    protected Object createValue(int n) {
        return new TimecardMinutes(n);
    }

}
