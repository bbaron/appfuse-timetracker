package com.bbaron.timetracker.service;

import java.util.Collection;

import com.bbaron.timetracker.model.*;

public interface TimecardService {

    Long createTimecard(Long submitterId, TimecardDate startDate);

    void enterTimeAllocation(Long timecardId, TimeAllocation alloc);

    Timecard getTimecard(Long timecardId);
    
    Timecard getLatestTimecard(Long submitterId);

    Timecard getLatestTimecard(String submitterUsername);

    Collection<User> getAllUsers();

    Collection<String> getAllTasks();

    Collection<String> getAllStatuses();

    Collection<Timecard> searchTimecards(TimecardSearchCriteria criteria);

    void submitTimecard(Long timecardId);

    void approveTimecard(Long timecardId, Long approverId);

    void rejectTimecard(Long timecardId);

    void saveTimecard(Timecard timecard);
}
