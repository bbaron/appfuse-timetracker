package com.bbaron.timetracker.dao;

import com.bbaron.timetracker.model.Timecard;

public interface TimecardDao extends GenericDao<Timecard, Long> {

    Timecard findLatest(Long submitterId);

    Timecard findById(Long timecardId);
}
