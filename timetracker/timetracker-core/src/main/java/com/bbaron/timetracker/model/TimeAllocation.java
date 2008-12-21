package com.bbaron.timetracker.model;

import java.io.Serializable;

import javax.persistence.*;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.bbaron.timetracker.temporal.TimecardDate;
import com.bbaron.timetracker.temporal.TimecardDuration;
import com.bbaron.timetracker.temporal.TimecardHours;
import com.bbaron.timetracker.temporal.TimecardMinutes;

@Embeddable
public class TimeAllocation implements Serializable {

	private static final long serialVersionUID = 33779457716057568L;
	private TimecardDate taskDate;
	private TimecardHours hours = TimecardHours.ZERO;
	private TimecardMinutes minutes = TimecardMinutes.ZERO;
	private Task task;

    @org.hibernate.annotations.Type(type = "com.bbaron.timetracker.model.hibernate.TimecardHoursUserType")
	@Column(nullable = false, name = "hours")
	public TimecardHours getHours() {
		return hours;
	}

	public void setHours(TimecardHours hours) {
		this.hours = hours;
	}

    @org.hibernate.annotations.Type(type = "com.bbaron.timetracker.model.hibernate.TimecardMinutesUserType")
	@Column(nullable = false, name = "minutes")
	public TimecardMinutes getMinutes() {
		return minutes;
	}

	public void setMinutes(TimecardMinutes minutes) {
		this.minutes = minutes;
	}

	@Column(name = "task_date", nullable = false)
    @org.hibernate.annotations.Type(type = "com.bbaron.timetracker.model.hibernate.TimecardDateUserType")
	public TimecardDate getTaskDate() {
		return taskDate;
	}

	public void setTaskDate(TimecardDate taskDate) {
		this.taskDate = taskDate;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "task", nullable = false, length = 20)
	public Task getTask() {
		return task;
	}

	public void setTask(Task task) {
		this.task = task;
	}
	
	@Transient
	public TimecardDuration getDuration() {
	    TimecardDuration d = TimecardDuration.duration(hours, minutes);
	    return d;
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
		return new EqualsBuilder()
			.append(lhs.getTask(), rhs.getTask())
			.append(lhs.getTaskDate(), rhs.getTaskDate())
			.append(lhs.getHours(), rhs.getHours())
			.append(lhs.getMinutes(), rhs.getMinutes())
				.isEquals();
	}

	@Override
	public String toString() {
		ToStringBuilder sb = new ToStringBuilder(this,
				ToStringStyle.DEFAULT_STYLE)
				.append("task",	getTask())
				.append("taskDate",	getTaskDate())
				.append("hours", getHours())
				.append("minutes", getMinutes());
		return sb.toString();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(getTask()).append(
				getTaskDate()).append(getTaskDate())
				.hashCode();
	}
}
