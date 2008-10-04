package com.bbaron.timetracker.service;

import java.util.Date;

public interface TimecardService {

	Long createTimecard(Long userId, Date startDate);
}
