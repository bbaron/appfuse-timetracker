package com.bbaron.timetracker.service;

import java.util.Collection;
import java.util.Date;

import com.bbaron.timetracker.model.TimeAllocation;
import com.bbaron.timetracker.model.Timecard;
import com.bbaron.timetracker.model.TimecardSearchCriteria;
import com.bbaron.timetracker.model.User;

public interface TimecardService {

    Long createTimecard(Long submitterId, Date startDate);

    void enterTimeAllocation(Long timecardId, TimeAllocation alloc);

    Timecard getTimecard(Long timecardId);
    
    Timecard getLatestTimecard(Long submitterId);

    Collection<User> getAllUsers();

    Collection<String> getAllTasks();

    Collection<String> getAllStatuses();

    Collection<Timecard> searchTimecards(TimecardSearchCriteria criteria);

    void submitTimecard(Long timecardId);

    void approveTimecard(Long timecardId, Long approverId);

    void rejectTimecard(Long timecardId);
}
