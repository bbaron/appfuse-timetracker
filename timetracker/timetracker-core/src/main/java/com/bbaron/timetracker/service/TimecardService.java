package com.bbaron.timetracker.service;

import java.util.Date;

import com.bbaron.timetracker.model.TimeAllocation;

public interface TimecardService {

	Long createTimecard(Long userId, Date startDate);

	void enterTimeAllocation(Long timecardId, TimeAllocation alloc);
}
