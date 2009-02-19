package com.bbaron.timetracker.model;

import static org.junit.Assert.*;

import org.joda.time.Duration;
import org.junit.Test;

import com.bbaron.timetracker.temporal.TimecardHours;
import com.bbaron.timetracker.temporal.TimecardMinutes;

public class TimeAllocationTest {

    @Test
    public void testGetDuration_hour() throws Exception {
        TimeAllocation alloc = new TimeAllocation();
        alloc.setHours(TimecardHours.hours(1));
        Duration actual = alloc.getDuration();
        Duration expected = Duration.standardHours(1);
        assertEquals(expected, actual);        
        alloc.setHours(TimecardHours.hours(23));
        actual = alloc.getDuration();
        expected = Duration.standardHours(23);
        assertEquals(expected, actual);        
    }

    @Test
    public void testGetDuration_minute() throws Exception {
        TimeAllocation alloc = new TimeAllocation();
        alloc.setMinutes(TimecardMinutes.minutes(1));
        Duration actual = alloc.getDuration();
        Duration expected = Duration.standardMinutes(1);
        assertEquals(expected, actual);        
        alloc.setMinutes(TimecardMinutes.minutes(23));
        actual = alloc.getDuration();
        expected = Duration.standardMinutes(23);
        assertEquals(expected, actual);        
    }

    @Test
    public void testGetDuration_hours_minutes() throws Exception {
        TimeAllocation alloc = new TimeAllocation();
        alloc.setHours(TimecardHours.hours(1));
        alloc.setMinutes(TimecardMinutes.minutes(1));
        Duration actual = alloc.getDuration();
        Duration expected = Duration.standardMinutes(1).plus(Duration.standardHours(1));
        assertEquals(expected, actual);        
        alloc.setHours(TimecardHours.hours(23));
        alloc.setMinutes(TimecardMinutes.minutes(23));
        actual = alloc.getDuration();
        expected = Duration.standardMinutes(23).plus(Duration.standardHours(23));
        assertEquals(expected, actual);        
    }
}
