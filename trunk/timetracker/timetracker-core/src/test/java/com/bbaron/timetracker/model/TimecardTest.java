package com.bbaron.timetracker.model;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.Date;

import org.joda.time.LocalDate;
import org.junit.Test;


public class TimecardTest {

    @Test
    public void testTimecardDefaultStatusIsDraft() throws Exception {
        Timecard timecard = new Timecard();
        assertEquals(TimecardStatus.Draft, timecard.getStatus());
    }
    
    @Test
	public void testDateSelection() throws Exception {
		Timecard t = new Timecard();
		Calendar c = Calendar.getInstance();
		LocalDate localdate = new LocalDate(2005, 12, 31);
		c.set(2005, Calendar.DECEMBER, 31);
		Date startDate = c.getTime();
		t.setStartDate(localdate);
        System.out.println(t);
		String[] dates = t.getDateSelection();
		assertEquals("2005-12-31", dates[0]);
		assertEquals("2006-01-01", dates[1]);
		assertEquals("2006-01-06", dates[6]);
	}
}
