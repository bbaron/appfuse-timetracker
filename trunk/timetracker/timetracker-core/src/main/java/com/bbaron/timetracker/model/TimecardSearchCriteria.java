package com.bbaron.timetracker.model;

import java.util.Date;

public class TimecardSearchCriteria {

	private Long submitterId;
	private Long approverId;
	private TimecardStatus status;
	private Date startDateMin;
	private Date startDateMax;

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

	public Date getStartDateMin() {
		return startDateMin;
	}

	public void setStartDateMin(Date startDateMin) {
		this.startDateMin = startDateMin;
	}

	public Date getStartDateMax() {
		return startDateMax;
	}

	public void setStartDateMax(Date startDateMax) {
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
