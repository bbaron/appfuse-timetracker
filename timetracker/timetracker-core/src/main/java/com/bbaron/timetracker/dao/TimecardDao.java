package com.bbaron.timetracker.dao;

import java.util.Collection;

import com.bbaron.timetracker.model.Timecard;
import com.bbaron.timetracker.model.TimecardSearchCriteria;

public interface TimecardDao extends GenericDao<Timecard, Long> {

    Timecard findLatest(Long submitterId);

    Timecard findById(Long timecardId);
    
    Collection<Timecard> findByCriteria(TimecardSearchCriteria criteria);
}
