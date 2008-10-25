package com.bbaron.timetracker.dao.hibernate;

import org.hibernate.Hibernate;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.bbaron.timetracker.model.Timecard;
import com.bbaron.timetracker.model.User;
import com.bbaron.timetracker.service.TimecardFactory;

@Repository("timecardFactory")
public class TimecardFactoryHibernate extends HibernateDaoSupport implements TimecardFactory {

    @Override
    public void initialize(Timecard timecard, boolean includeDetail) {
        User approver = timecard.getApprover();
        if (approver != null) {
            Hibernate.initialize(approver);
        }
        Hibernate.initialize(timecard.getSubmitter());
        if (includeDetail) {
            Hibernate.initialize(timecard.getTimeAllocations());
        }
    }

    @Autowired
    public void setAutoWiredSessionFactory(SessionFactory factory) {
        super.setSessionFactory(factory);
    }

}
