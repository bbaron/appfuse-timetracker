package com.bbaron.timetracker.web.commands;

import java.io.Serializable;
import java.util.Date;

import com.bbaron.timetracker.model.Task;
import com.bbaron.timetracker.model.TimeAllocation;

public class TimecardEntry implements Serializable {

	private static final long serialVersionUID = 7960060982571986009L;
	private TimeAllocation alloc = new TimeAllocation();
	private Long timecardId;

	public TimeAllocation getTimeAllocation() {
		return alloc;
	}
	
	public Long getTimecardId() {
		return timecardId;
	}

	public void setTimecardId(Long timecardId) {
		this.timecardId = timecardId;
	}

	public Task getTask() {
		return alloc.getTask();
	}

	public Date getTimePeriodEndTime() {
		return alloc.getTimePeriodEndTime();
	}

	public Date getTimePeriodStartTime() {
		return alloc.getTimePeriodStartTime();
	}

	public void setTask(Task task) {
		alloc.setTask(task);
	}

	public void setTimePeriodEndTime(Date timePeriodEndTime) {
		alloc.setTimePeriodEndTime(timePeriodEndTime);
	}

	public void setTimePeriodStartTime(Date timePeriodStartTime) {
		alloc.setTimePeriodStartTime(timePeriodStartTime);
	}

	@Override
	public String toString() {
		return alloc.toString();
	}

}
