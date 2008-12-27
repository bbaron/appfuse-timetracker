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

    void submitTimecard(Timecard timecard);

    void approveTimecard(Timecard timecard, String approver);

    void rejectTimecard(Timecard timecard);

    void saveTimecard(Timecard timecard);
    
    void deleteTimecard(Long timecardId);
}
