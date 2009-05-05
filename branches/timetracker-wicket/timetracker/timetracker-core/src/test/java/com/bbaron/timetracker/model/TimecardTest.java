package com.bbaron.timetracker.model;

import static org.junit.Assert.*;

import java.util.Calendar;

import org.joda.time.Period;
import org.junit.Test;

import com.bbaron.timetracker.temporal.TimecardDate;
import com.bbaron.timetracker.temporal.TimecardHours;


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
		TimecardDate localdate = TimecardDate.date(2005, 12, 31);
		c.set(2005, Calendar.DECEMBER, 31);
		t.setStartDate(localdate);
        System.out.println(t);
		String[] dates = t.getDateSelection();
		assertEquals("2005-12-31", dates[0]);
		assertEquals("2006-01-01", dates[1]);
		assertEquals("2006-01-06", dates[6]);
	}
    
    @Test
    public void testGetPeriod() throws Exception {
        Timecard t = new Timecard();
        TimeAllocation alloc1 = new TimeAllocation();
        alloc1.setHours(TimecardHours.hours(3));
        t.addTimeAllocation(alloc1);
        Period actual = t.getPeriod();
        assertEquals(3, actual.getHours());
    }
}
