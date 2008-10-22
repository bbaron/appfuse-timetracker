package com.bbaron.timetracker.service;

import java.util.Collection;
import java.util.Date;
import java.util.Map;

import com.bbaron.timetracker.model.TimeAllocation;
import com.bbaron.timetracker.model.Timecard;

public interface TimecardService {

    Long createTimecard(Long userId, Date startDate);

    void enterTimeAllocation(Long timecardId, TimeAllocation alloc);

    Timecard getTimecard(Long timecardId);

    Map<Long, String> getAllUsers();

    Collection<String> getAllTasks();

    Collection<String> getAllStatuses();
}
