package com.bbaron.timetracker.dao;

import java.util.Date;

import com.bbaron.timetracker.model.Timecard;
import com.bbaron.timetracker.model.User;

public class TimecardDaoTest extends AbstractGenericDaoTestCase<Timecard, Long> {

	public TimecardDaoTest() {
		super(Timecard.class, -1L, 1000L);
	}

	@Override
	protected Timecard createEntity() {
		Timecard timecard = new Timecard();
		timecard.setComments("comment");
		timecard.setStartDate(new Date());
		timecard.setStatus("Draft");
		User submitter = (User) getSessionFactory().getCurrentSession().get(
				User.class, 1L);
		timecard.setSubmitter(submitter);
		return timecard;
	}

	@Override
	protected Object getActualFrom(Timecard timecard) {
		return timecard.getStatus();
	}

	@Override
	protected Object getExpected() {
		return "Draft";
	}

	@Override
	protected Long getIdFrom(Timecard timecard) {
		return timecard.getId();
	}

	@Override
	protected void clearIdFrom(Timecard timecard) {
		timecard.setId(null);
	}

}
