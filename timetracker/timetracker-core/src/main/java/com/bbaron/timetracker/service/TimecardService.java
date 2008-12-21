package com.bbaron.timetracker.service;

import java.util.Collection;

import com.bbaron.timetracker.model.*;
import com.bbaron.timetracker.temporal.TimecardDate;

public interface TimecardService {

    Long createTimecard(String submitter, TimecardDate startDate);

    void enterTimeAllocation(Long timecardId, TimeAllocation alloc);

    Timecard getTimecard(Long timecardId);
    
    Timecard getLatestTimecard(String submitter);

    Collection<User> getAllUsers();

    Collection<String> getAllTasks();

    Collection<String> getAllStatuses();

    Collection<Timecard> searchTimecards(TimecardSearchCriteria criteria);

    void submitTimecard(Long timecardId);

    void approveTimecard(Long timecardId, String approver);

    void rejectTimecard(Long timecardId);

    void saveTimecard(Timecard timecard);
}
