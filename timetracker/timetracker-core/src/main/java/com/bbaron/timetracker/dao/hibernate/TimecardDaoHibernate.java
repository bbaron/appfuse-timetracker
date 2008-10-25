package com.bbaron.timetracker.dao.hibernate;

import org.springframework.stereotype.Repository;

import com.bbaron.timetracker.dao.TimecardDao;
import com.bbaron.timetracker.model.Timecard;

@Repository("timecardDao")
public class TimecardDaoHibernate extends GenericDaoHibernate<Timecard, Long> implements TimecardDao {

    public TimecardDaoHibernate() {
        super(Timecard.class);
    }

}
