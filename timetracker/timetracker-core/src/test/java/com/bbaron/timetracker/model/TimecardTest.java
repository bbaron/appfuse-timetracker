package com.bbaron.timetracker.model;

import static org.junit.Assert.*;

import org.junit.Test;


public class TimecardTest {

    @Test
    public void testTimecardDefaultStatusIsDraft() throws Exception {
        Timecard timecard = new Timecard();
        assertEquals(TimecardStatus.Draft, timecard.getStatus());
    }
}
