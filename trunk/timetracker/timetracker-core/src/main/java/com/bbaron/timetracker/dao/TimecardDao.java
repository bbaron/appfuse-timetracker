package com.bbaron.timetracker.dao;

import java.util.Collection;

import com.bbaron.timetracker.model.Timecard;
import com.bbaron.timetracker.model.TimecardSearchCriteria;

public interface TimecardDao extends GenericDao<Timecard, Long> {

    Timecard findLatest(String submitter);

    Timecard findById(Long timecardId);
    
    Collection<Timecard> findSubmitted(String approver);
    
    Collection<Timecard> findByCriteria(TimecardSearchCriteria criteria);
}
