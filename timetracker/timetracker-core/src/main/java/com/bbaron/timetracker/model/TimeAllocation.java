package com.bbaron.timetracker.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

@Embeddable
public class TimeAllocation implements Serializable {

	private static final long serialVersionUID = 33779457716057568L;
	private Date timePeriodStartTime;
	private Date timePeriodEndTime;
	private Task task;

	@Column(name = "time_period_start_time", nullable = false)
	public Date getTimePeriodStartTime() {
		return timePeriodStartTime;
	}

	public void setTimePeriodStartTime(Date timePeriodStartTime) {
		this.timePeriodStartTime = timePeriodStartTime;
	}

	@Column(name = "time_period_end_time", nullable = false)
	public Date getTimePeriodEndTime() {
		return timePeriodEndTime;
	}

	public void setTimePeriodEndTime(Date timePeriodEndTime) {
		this.timePeriodEndTime = timePeriodEndTime;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "task", nullable = false, length = 20)
	public Task getTask() {
		return task;
	}

	public void setTask(Task task) {
		this.task = task;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj instanceof TimeAllocation == false) {
			return false;
		}
		final TimeAllocation lhs = this;
		final TimeAllocation rhs = (TimeAllocation) obj;
		return new EqualsBuilder().append(lhs.getTask(), rhs.getTask()).append(
				lhs.getTimePeriodStartTime(), rhs.getTimePeriodStartTime())
				.append(lhs.getTimePeriodEndTime(), rhs.getTimePeriodEndTime())
				.isEquals();
	}

	@Override
	public String toString() {
		ToStringBuilder sb = new ToStringBuilder(this,
				ToStringStyle.DEFAULT_STYLE).append("task",
				this.getTask().name()).append("start",
				this.getTimePeriodStartTime()).append("end",
				this.getTimePeriodEndTime());
		return sb.toString();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(getTask()).append(
				getTimePeriodEndTime()).append(getTimePeriodEndTime())
				.hashCode();
	}
}
