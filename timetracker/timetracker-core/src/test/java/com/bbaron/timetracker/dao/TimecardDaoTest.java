package com.bbaron.timetracker.dao;

import java.util.Collection;

import org.hibernate.LazyInitializationException;
import org.joda.time.LocalDate;

import com.bbaron.timetracker.dao.hibernate.GenericDaoHibernate;
import com.bbaron.timetracker.dao.hibernate.TimecardDaoHibernate;
import com.bbaron.timetracker.model.*;

public class TimecardDaoTest extends AbstractGenericDaoTestCase<Timecard, Long> {

    private TimecardDaoHibernate timecardDao;

    public TimecardDaoTest() {
        super(Timecard.class, -1L, 1000L);
    }

    @Override
    protected GenericDaoHibernate<Timecard, Long> createDao(Class<Timecard> entityClass) {
        this.timecardDao = new TimecardDaoHibernate();
        return this.timecardDao;
    }

    @Override
    protected Timecard createEntity() {
        LocalDate date = new LocalDate();
        Timecard timecard = new Timecard();
        timecard.setComments("comment");
        timecard.setStartDate(date);
        timecard.setStatus(TimecardStatus.Submitted);
        TimeAllocation alloc = new TimeAllocation();
        alloc.setTask(Task.Development);
        alloc.setTaskDate(date);
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
                "insert into tt_user (id, first_name, last_name, username) values (-2, 'first2', 'last2', 'user2')",
                "insert into tt_user (id, first_name, last_name, username) values (-3, 'first3', 'last3', 'user3')",
                "insert into tt_timecard (id, status, start_date, comments, approver_id, submitter_id) "
                        + "values (-1, 'Draft', '2007/11/15', 'Timecard -1', null, -1)",
                "insert into tt_timecard (id, status, start_date, comments, approver_id, submitter_id) "
                        + "values (-2, 'Draft', '2007/11/22', 'Timecard -2', -2, -1)",
                "insert into tt_timecard (id, status, start_date, comments, approver_id, submitter_id) "
                        + "values (-3, 'Draft', '2007/11/23', null, null, -3)",
                "insert into tt_timecard_alloc (timecard_id, task_date, hours, minutes, task, position) "
                        + "values (-1, '2007/11/15', 4, 20, 'Admin', 0)",
                "insert into tt_timecard_alloc (timecard_id, task_date, hours, minutes, task, position) "
                        + "values (-1, '2007/11/16', 1, 0, 'Meeting', 1)",
                "insert into tt_timecard_alloc (timecard_id, task_date, hours, minutes, task, position) "
                        + "values (-2, '2007/11/22', 4, 20, 'Admin', 0)",
                "insert into tt_timecard_alloc (timecard_id, task_date, hours, minutes, task, position) "
                        + "values (-2, '2007/11/23', 1, 0, 'Meeting', 1)",

        };
    }

    @Override
    protected void updateEntity(Timecard timecard) {
        timecard.setStatus(TimecardStatus.Approved);
    }

    public void testFindLatest() throws Exception {
        Timecard latest = timecardDao.findLatest(-1L);
        assertEquals(-2, latest.getId().intValue());
        super.endTransaction();
        try {
            latest.getSubmitter().getFirstName();
            latest.getApprover().getFirstName();
            latest.getTimeAllocations().size();
        } catch (LazyInitializationException e) {
            fail("timecard not fully initialized: " + e.toString());
        }
    }

    public void testFindById() throws Exception {
        Timecard timecard = timecardDao.findById(-2L);
        assertEquals(-2, timecard.getId().intValue());
        super.endTransaction();
        try {
            timecard.getSubmitter().getFirstName();
            timecard.getApprover().getFirstName();
            timecard.getTimeAllocations().size();
        } catch (LazyInitializationException e) {
            fail("timecard not fully initialized: " + e.toString());
        }
    }

    public void testFindLatest_noAssociations() throws Exception {
        Timecard latest = timecardDao.findLatest(-3L);
        flush();
        endTransaction();
        assertNull(latest.getApprover());
        assertTrue(latest.getTimeAllocations().isEmpty());
    }

    public void testNoLastTimecard() throws Exception {
        try {
            assertNull(timecardDao.findLatest(-999L));
        } catch (Exception e) {
            e.printStackTrace();
            fail("findLatest should return null when nothing found " + e.toString());
        }
    }

    public void testFindByCriteriaSubmitter() throws Exception {
        TimecardSearchCriteria criteria = new TimecardSearchCriteria();
        criteria.setSubmitterId(-1L);
        assertTestFindByCriteria(criteria, 2);
    }

    public void testFindByCriteriaApprover() throws Exception {
        TimecardSearchCriteria criteria = new TimecardSearchCriteria();
        criteria.setApproverId(-2L);
        assertTestFindByCriteria(criteria, 1);
    }

    public void testFindByCriteriaDate() throws Exception {
        TimecardSearchCriteria criteria = new TimecardSearchCriteria();
        LocalDate nov15 = new LocalDate(2007,11,15);
        LocalDate nov22 = new LocalDate(2007,11,22);
        criteria.setStartDateMin(nov15);
        criteria.setStartDateMax(nov22);
        assertTestFindByCriteria(criteria, 2);
        criteria.setStartDateMin(nov22);
        criteria.setStartDateMax(nov15);
        assertTestFindByCriteria(criteria, 0);
        criteria.setStartDateMin(nov22);
        criteria.setStartDateMax(nov22);
        assertTestFindByCriteria(criteria, 1);
    }

    public void testFindByCriteriaAll() throws Exception {
        TimecardSearchCriteria criteria = new TimecardSearchCriteria();
        Collection<Timecard> result = timecardDao.findByCriteria(criteria);
        assertFalse(result.isEmpty());
    }

    private void assertTestFindByCriteria(TimecardSearchCriteria criteria, int expectedSize) {
        Collection<Timecard> result = timecardDao.findByCriteria(criteria);
        assertEquals(expectedSize, result.size());
    }
}
