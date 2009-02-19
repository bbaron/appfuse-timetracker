package com.bbaron.timetracker.model;

public enum TimecardStatus {
	Draft, Submitted, Approved, Rejected;

    public boolean isUpdatable() {
        return this == Draft || this == Rejected;
    }
}
