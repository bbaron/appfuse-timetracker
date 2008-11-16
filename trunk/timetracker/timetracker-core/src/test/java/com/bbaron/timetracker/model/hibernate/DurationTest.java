package com.bbaron.timetracker.model.hibernate;

import static org.junit.Assert.*;

import org.joda.time.DateTimeConstants;
import org.joda.time.Duration;
import org.joda.time.field.FieldUtils;
import org.joda.time.format.PeriodFormat;
import org.joda.time.format.PeriodFormatter;
import org.junit.Test;

public class DurationTest {

    @Test
    public void duration() throws Exception {
        Duration d = new Duration(FieldUtils.safeMultiply(3, DateTimeConstants.MILLIS_PER_HOUR));
        d = d.plus(FieldUtils.safeMultiply(30, DateTimeConstants.MILLIS_PER_MINUTE));
        PeriodFormatter f = PeriodFormat.getDefault();
        System.out.println(d);
        Duration d2 = new Duration(d.toString());
        System.out.println(d2);
        assertEquals(d,d2);
    }
}
