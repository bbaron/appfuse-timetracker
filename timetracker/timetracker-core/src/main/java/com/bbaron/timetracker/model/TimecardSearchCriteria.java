package com.bbaron.timetracker.model;

public class TimecardSearchCriteria {

	private String submitter;
	private String approver;
	private TimecardStatus status;
	private TimecardDate startDateMin;
	private TimecardDate startDateMax;

	public String getSubmitter() {
		return submitter;
	}

	public void setSubmitter(String submitter) {
		this.submitter = submitter;
	}

	public String getApprover() {
		return approver;
	}

	public void setApprover(String approver) {
		this.approver = approver;
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
        if (submitter != null) {
            s.append(" submitter = " + submitter);
        }
        if (approver != null) {
            s.append(" approver = " + approver);
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
