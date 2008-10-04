package com.bbaron.timetracker.service;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import com.bbaron.timetracker.dao.mock.MockTimecardDao;
import com.bbaron.timetracker.dao.mock.MockUserDao;
import com.bbaron.timetracker.model.User;

public class TimecardServiceTest {

    private TimecardServiceImpl timecardService;
    private MockTimecardDao timecardDao;
    private MockUserDao userDao;

    @Before
    public void setUp() throws Exception {
        timecardService = new TimecardServiceImpl();
        timecardDao = new MockTimecardDao();
        userDao = new MockUserDao();
        timecardService.setTimecardDao(timecardDao);
        timecardService.setUserDao(userDao);
    }

    @Test
    public void testCreateTimecard() throws Exception {
        User user = new User();
        user.setId(1L);
        userDao.setEntities(user);
        Date startDate = new Date();
        Long expected = 1L;
        timecardDao.setId(expected);
        Long actual = timecardService.createTimecard(user.getId(), startDate);
        assertEquals(expected, actual);
    }
}
