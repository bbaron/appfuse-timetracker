package com.bbaron.timetracker.dao.hibernate;

import java.util.Date;
import java.util.List;

import org.springframework.dao.support.DataAccessUtils;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.bbaron.timetracker.dao.TimecardDao;
import com.bbaron.timetracker.model.Timecard;

@Repository("timecardDao")
public class TimecardDaoHibernate extends GenericDaoHibernate<Timecard, Long>
		implements TimecardDao {

	public TimecardDaoHibernate() {
		super(Timecard.class);
	}

	@Override
	public Timecard findLastSaved(Long submitterId) {
		HibernateTemplate template = getHibernateTemplate();
//		Date latestDate = (Date) DataAccessUtils.objectResult(template
//				.find("select max(t.startDate) from Timecard t where t.submitter.id = ?", new Object[] { submitterId }), Date.class);
		String hql = "from Timecard as timecard inner join fetch timecard.submitter as submitter"
				+ " left join fetch timecard.approver left join fetch timecard.timeAllocations"
				+ " where submitter.id = ?";// and timecard.startDate = ?";
		List<?> results = template.find(hql, new Object[] { submitterId });
		if (results == null || results.isEmpty()) {
			return null;
		}
		return (Timecard) results.get(0);
	}

}
