package com.bbaron.timetracker.model;

import java.io.Serializable;

import javax.persistence.*;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.joda.time.*;

@Embeddable
public class TimeAllocation implements Serializable {

	private static final long serialVersionUID = 33779457716057568L;
	private LocalDate taskDate;
	private Hours hours = Hours.ZERO;
	private Minutes minutes = Minutes.ZERO;
	private Task task;

    @org.hibernate.annotations.Type(type = "com.bbaron.timetracker.model.hibernate.HoursUserType")
	@Column(nullable = false, name = "hours")
	public Hours getHours() {
		return hours;
	}

	public void setHours(Hours hours) {
		this.hours = hours;
	}

    @org.hibernate.annotations.Type(type = "com.bbaron.timetracker.model.hibernate.MinutesUserType")
	@Column(nullable = false, name = "minutes")
	public Minutes getMinutes() {
		return minutes;
	}

	public void setMinutes(Minutes minutes) {
		this.minutes = minutes;
	}

	@Column(name = "task_date", nullable = false)
    @org.hibernate.annotations.Type(type = "com.bbaron.timetracker.model.hibernate.LocalDateUserType")
	public LocalDate getTaskDate() {
		return taskDate;
	}

	public void setTaskDate(LocalDate taskDate) {
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
	public Duration getDuration() {
	    Duration d = hours.toStandardDuration().plus(minutes.toStandardDuration());
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
