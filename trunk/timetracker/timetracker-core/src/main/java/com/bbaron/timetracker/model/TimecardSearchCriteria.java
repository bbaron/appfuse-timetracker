package com.bbaron.timetracker.model;

public class TimecardSearchCriteria {

	private Long submitterId;
	private Long approverId;
	private TimecardStatus status;
	private TimecardDate startDateMin;
	private TimecardDate startDateMax;

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

	public TimecardDate getStartDateMin() {
		return startDateMin;
	}

	public void setStartDateMin(TimecardDate startDateMin) {
		this.startDateMin = startDateMin;
	}

	public TimecardDate getStartDateMax() {
		return startDateMax;
	}

	public void setStartDateMax(TimecardDate startDateMax) {
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
