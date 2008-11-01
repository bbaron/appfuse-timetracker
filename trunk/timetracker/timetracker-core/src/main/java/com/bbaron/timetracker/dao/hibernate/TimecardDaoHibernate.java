package com.bbaron.timetracker.dao.hibernate;

import java.util.Collection;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.bbaron.timetracker.dao.TimecardDao;
import com.bbaron.timetracker.model.Timecard;
import com.bbaron.timetracker.model.TimecardSearchCriteria;

@Repository("timecardDao")
public class TimecardDaoHibernate extends GenericDaoHibernate<Timecard, Long>
		implements TimecardDao {

	public TimecardDaoHibernate() {
		super(Timecard.class);
	}

    /*
     * select t1.start_date 
     * ,      t2.task as alloc_task 
     * ,      t3.username as submitter
     * ,      t3.id as submitter_id 
     * ,      t4.username as approver from tt_timecard as t1
     * left join tt_timecard_alloc as t2 on t1.id = t2.timecard_id 
     * left join tt_user as t4 on t4.id = t1.approver_id 
     * inner join tt_user as t3 on t3.id = t1.submitter_id 
     * where t1.start_date = (
     *           select max(start_date) 
     *           from tt_timecard 
     *           where submitter_id = :submitterId) 
     * and   t3.id = :submitterId
     */
    @Override
    @SuppressWarnings("unchecked")
	public Timecard findLatest(Long submitterId) {
		HibernateTemplate template = getHibernateTemplate();
		List results = template.findByNamedQueryAndNamedParam("latestTimecard", "submitterId", submitterId);
		Timecard result = (Timecard) DataAccessUtils.uniqueResult(results);
		return result;
	}

    @SuppressWarnings("unchecked")
    @Override
    public Timecard findById(Long timecardId) {
		HibernateTemplate template = getHibernateTemplate();
		List results = template.findByNamedQueryAndNamedParam("timecard", "timecardId", timecardId);
		Timecard result = (Timecard) DataAccessUtils.requiredUniqueResult(results);
		return result;
    }

    @Override
    public Collection<Timecard> findByCriteria(TimecardSearchCriteria criteria) {
        // Create the timecard criteria
        Criteria timecardCriteria = this.getSession().createCriteria(
                Timecard.class).setFetchMode("submitter", FetchMode.JOIN)
                .setFetchMode("approver", FetchMode.JOIN);

        // Add submitter criteria
        if (criteria.getSubmitterId() != null) {
            timecardCriteria.createCriteria("submitter").add(
                    Restrictions.idEq(criteria.getSubmitterId()));
        }

        // Add submitter criteria
        if (criteria.getApproverId() != null) {
            timecardCriteria.createCriteria("approver").add(
                    Restrictions.idEq(criteria.getApproverId()));
        }

        // Add status criteria
        if (criteria.getStatus() != null) {
            timecardCriteria.add(Restrictions.eq("status", criteria.getStatus()));
        }

        // Add startDateMin criteria
        if (criteria.getStartDateMin() != null) {
            timecardCriteria.add(
                Restrictions.ge("startDate", criteria.getStartDateMin()));
        }

        // Add startDateMax criteria
        if (criteria.getStartDateMax() != null) {
            timecardCriteria.add(
                Restrictions.le("startDate", criteria.getStartDateMax()));
        }

        @SuppressWarnings("unchecked")
        Collection<Timecard> timecards = timecardCriteria.list();
        if (logger.isDebugEnabled()) {
            logger.debug(timecards.size() + " timecards found");
        }
        return timecards;
    }

}
