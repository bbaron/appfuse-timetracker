package com.bbaron.timetracker.service;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import com.bbaron.timetracker.dao.mock.MockTimecardDao;
import com.bbaron.timetracker.dao.mock.MockUserDao;
import com.bbaron.timetracker.model.Task;
import com.bbaron.timetracker.model.TimeAllocation;
import com.bbaron.timetracker.model.Timecard;
import com.bbaron.timetracker.model.User;

public class TimecardServiceTest {

    private TimecardServiceImpl timecardService;
    private MockTimecardDao timecardDao;
    private MockUserDao userDao;
	private User user;
	private static final Long TIMECARD_ID = 1L;
	private static final Long USER_ID = 1L;

    @Before
    public void setUp() throws Exception {
        timecardService = new TimecardServiceImpl();
        timecardDao = new MockTimecardDao();
        userDao = new MockUserDao();
        timecardService.setTimecardDao(timecardDao);
        timecardService.setUserDao(userDao);
        user = new User("user");
        user.setId(USER_ID);
        userDao.setEntities(user);
    }

    @Test
    public void testCreateTimecard() throws Exception {
        Date startDate = new Date();
        Long expected = TIMECARD_ID;
        timecardDao.setId(expected);
        Long actual = timecardService.createTimecard(USER_ID, startDate);
        assertEquals(expected, actual);
    }
    
    @Test
	public void testEnterTimeAllocation() throws Exception {
		Long timecardId = TIMECARD_ID;
		Date startTime = new Date();
		Date endTime = new Date();
		Task task = Task.Admin;
		TimeAllocation alloc = new TimeAllocation();
		alloc.setTask(task);
		alloc.setTimePeriodStartTime(startTime);
		alloc.setTimePeriodEndTime(endTime);
		Timecard timecard = new Timecard();
		timecard.setStartDate(startTime);
		timecard.setSubmitter(user);
		timecardDao.setId(TIMECARD_ID);
		timecardDao.setEntities(timecard);
		timecardService.enterTimeAllocation(timecardId, alloc);
		assertEquals(1, timecard.getTimeAllocationList().size());
	}
}
