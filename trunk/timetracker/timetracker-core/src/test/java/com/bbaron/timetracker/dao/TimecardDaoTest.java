package com.bbaron.timetracker.dao;

import java.util.Calendar;
import java.util.GregorianCalendar;

import com.bbaron.timetracker.model.*;

public class TimecardDaoTest extends AbstractGenericDaoTestCase<Timecard, Long> {

    public TimecardDaoTest() {
        super(Timecard.class, -1L, 1000L);
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
        User submitter = (User) getSessionFactory().getCurrentSession().get(User.class, -1L);
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
                "insert into tt_timecard (id, status, start_date, comments, approver_id, submitter_id) "
                        + "values (-1, 'Draft', '2006/06/05', 'Timecard -1', null, -1)"

        };
    }

	@Override
	protected void updateEntity(Timecard timecard) {
		timecard.setStatus(TimecardStatus.Approved);
	}

}
