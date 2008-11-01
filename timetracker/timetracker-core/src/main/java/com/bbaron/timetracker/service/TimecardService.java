package com.bbaron.timetracker.service;

import java.util.Collection;
import java.util.Date;
import java.util.Map;

import com.bbaron.timetracker.model.TimeAllocation;
import com.bbaron.timetracker.model.Timecard;
import com.bbaron.timetracker.model.TimecardSearchCriteria;

public interface TimecardService {

    Long createTimecard(Long submitterId, Date startDate);

    void enterTimeAllocation(Long timecardId, TimeAllocation alloc);

    Timecard getTimecard(Long timecardId);
    
    Timecard getLatestTimecard(Long submitterId);

    Map<Long, String> getAllUsers();

    Collection<String> getAllTasks();

    Collection<String> getAllStatuses();

    Collection<Timecard> searchTimecards(TimecardSearchCriteria criteria);
}
