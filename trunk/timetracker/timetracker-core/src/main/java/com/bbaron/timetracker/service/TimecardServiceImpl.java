package com.bbaron.timetracker.service;

import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bbaron.timetracker.dao.GenericDao;
import com.bbaron.timetracker.model.TimeAllocation;
import com.bbaron.timetracker.model.Timecard;
import com.bbaron.timetracker.model.User;

@Service
public class TimecardServiceImpl implements TimecardService {

	private GenericDao<Timecard, Long> timecardDao;
	private GenericDao<User, Long> userDao;
	protected Logger logger = Logger.getLogger(getClass());

    @Autowired
	public void setUserDao(GenericDao<User, Long> userDao) {
		this.userDao = userDao;
	}

    @Autowired
	public void setTimecardDao(GenericDao<Timecard, Long> timecardDao) {
		this.timecardDao = timecardDao;
	}

	@Override
	public Long createTimecard(Long userId, Date startDate) {
		User submitter = userDao.get(userId);
		if (logger.isInfoEnabled()) {
		    logger.info("creating timecard for user " + submitter + " starting " + startDate);
		}
		Timecard timecard = new Timecard();
		timecard.setSubmitter(submitter);
		timecard.setStartDate(startDate);
		Timecard saved = timecardDao.save(timecard);
		return saved.getId();
	}

	@Override
	public void enterTimeAllocation(Long timecardId, TimeAllocation alloc) {
		Timecard timecard = timecardDao.get(timecardId);
		timecard.addTimeAllocation(alloc);
	}

}
