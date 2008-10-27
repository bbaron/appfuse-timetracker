package com.bbaron.timetracker.dao;

import java.util.Calendar;
import java.util.GregorianCalendar;

import com.bbaron.timetracker.dao.hibernate.GenericDaoHibernate;
import com.bbaron.timetracker.dao.hibernate.TimecardDaoHibernate;
import com.bbaron.timetracker.model.*;

public class TimecardDaoTest extends AbstractGenericDaoTestCase<Timecard, Long> {

	private TimecardDaoHibernate timecardDao;
	
	public TimecardDaoTest() {
		super(Timecard.class, -1L, 1000L);
	}

	@Override
	protected GenericDaoHibernate<Timecard, Long> createDao(
			Class<Timecard> entityClass) {
		this.timecardDao = new TimecardDaoHibernate();
		return this.timecardDao;
	}

	@Override
	protected Timecard createEntity() {
		Calendar cal = new GregorianCalendar();
		Timecard timecard = new Timecard();
		timecard.setComments("comment");
		timecard.setStartDate(cal.getTime());
		timecard.setStatus(TimecardStatus.Submitted);
		TimeAllocation alloc = new TimeAllocation();
		alloc.setTask(Task.Development);
		alloc.setTaskDate(cal.getTime());
		alloc.setHours(3);
		alloc.setMinutes(15);
		timecard.addTimeAllocation(alloc);
		User submitter = (User) getSessionFactory().getCurrentSession().get(
				User.class, -1L);
		timecard.setSubmitter(submitter);
		return timecard;
	}

	@Override
	protected Object getActualFrom(Timecard timecard) {
		return timecard.getStatus();
	}

	@Override
	protected Object getExpected() {
		return TimecardStatus.Submitted;
	}

	@Override
	protected Long getIdFrom(Timecard timecard) {
		return timecard.getId();
	}

	@Override
	protected void clearIdFrom(Timecard timecard) {
		timecard.setId(null);
	}

	@Override
	protected String[] getSetUpStatements() {
		return new String[] {
				"insert into tt_user (id, first_name, last_name, username) values (-1, 'first1', 'last1', 'user1')",
				"insert into tt_user (id, first_name, last_name, username) values (-2, 'first2', 'last2', 'user2')",
				"insert into tt_timecard (id, status, start_date, comments, approver_id, submitter_id) "
						+ "values (-1, 'Draft', '2008/10/15', 'Timecard -1', null, -1)",
				"insert into tt_timecard (id, status, start_date, comments, approver_id, submitter_id) "
						+ "values (-2, 'Draft', '2008/10/22', 'Timecard -2', -2, -1)",
				"insert into tt_timecard_alloc (timecard_id, task_date, hours, minutes, task, position) "
						+ "values (-1, '2008/10/15', 4, 20, 'Admin', 0)",
				"insert into tt_timecard_alloc (timecard_id, task_date, hours, minutes, task, position) "
						+ "values (-1, '2008/10/16', 1, 0, 'Meeting', 1)",
				"insert into tt_timecard_alloc (timecard_id, task_date, hours, minutes, task, position) "
						+ "values (-2, '2008/10/22', 4, 20, 'Admin', 0)",
				"insert into tt_timecard_alloc (timecard_id, task_date, hours, minutes, task, position) "
						+ "values (-2, '2008/10/23', 1, 0, 'Meeting', 1)",

		};
	}

	@Override
	protected void updateEntity(Timecard timecard) {
		timecard.setStatus(TimecardStatus.Approved);
	}

	public void testFindLastSaved() throws Exception {
		Timecard latest = timecardDao.findLastSaved(2002L);
		assertEquals(-2, latest.getId().intValue());
	}
}
