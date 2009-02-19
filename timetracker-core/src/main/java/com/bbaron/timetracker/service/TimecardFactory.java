package com.bbaron.timetracker.service;

import com.bbaron.timetracker.model.Timecard;

public interface TimecardFactory {

    void initialize(Timecard timecard, boolean includeDetail);
}
