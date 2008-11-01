package com.bbaron.timetracker.model;

import java.util.Date;

public class TimecardSearchCriteria {

	private String submitter;
	private String approver;
	private TimecardStatus status;
	private Date startDateMin;
	private Date startDateMax;

	public String getSubmitter() {
		return submitter;
	}

	public void setSubmitter(String submitterId) {
		this.submitter = submitterId;
	}

	public String getApprover() {
		return approver;
	}

	public void setApprover(String approverId) {
		this.approver = approverId;
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

}
