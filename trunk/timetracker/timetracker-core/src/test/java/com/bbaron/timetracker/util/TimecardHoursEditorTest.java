package com.bbaron.timetracker.util;

import com.bbaron.timetracker.model.TimecardHours;

public class TimecardHoursEditorTest extends AbstractIntegralValueEditorTest {

    @Override
    protected IntegralValueEditor createEditor(boolean allowEmpty) {
        return new TimecardHoursEditor(allowEmpty);
    }

    @Override
    protected Object createValue(int n) {
        return new TimecardHours(n);
    }

}
