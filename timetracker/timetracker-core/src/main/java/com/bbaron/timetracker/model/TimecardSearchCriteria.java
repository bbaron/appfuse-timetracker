package com.bbaron.timetracker.model;

import org.joda.time.LocalDate;

public class TimecardSearchCriteria {

	private Long submitterId;
	private Long approverId;
	private TimecardStatus status;
	private LocalDate startDateMin;
	private LocalDate startDateMax;

	public Long getSubmitterId() {
		return submitterId;
	}

	public void setSubmitterId(Long submitterId) {
		this.submitterId = submitterId;
	}

	public Long getApproverId() {
		return approverId;
	}

	public void setApproverId(Long approverId) {
		this.approverId = approverId;
	}

	public TimecardStatus getStatus() {
		return status;
	}

	public void setStatus(TimecardStatus status) {
		this.status = status;
	}

	public LocalDate getStartDateMin() {
		return startDateMin;
	}

	public void setStartDateMin(LocalDate startDateMin) {
		this.startDateMin = startDateMin;
	}

	public LocalDate getStartDateMax() {
		return startDateMax;
	}

	public void setStartDateMax(LocalDate startDateMax) {
		this.startDateMax = startDateMax;
	}

	@Override
	public String toString() {
	    StringBuilder s = new StringBuilder();
        if (submitterId != null) {
            s.append(" submitter = " + submitterId);
        }
        if (approverId != null) {
            s.append(" approver = " + approverId);
        }
        if (startDateMin != null) {
            s.append(" min = " + startDateMin);
        }
        if (startDateMax != null) {
            s.append(" max = " + startDateMax);
        }
        if (status != null) {
            s.append(" status = " + status);
        }
	    return s.toString();
	}
}
