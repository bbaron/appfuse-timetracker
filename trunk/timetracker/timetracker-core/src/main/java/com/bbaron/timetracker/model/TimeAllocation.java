package com.bbaron.timetracker.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

@Embeddable
public class TimeAllocation implements Serializable {

	private static final long serialVersionUID = 33779457716057568L;
	private Date taskDate;
	private Integer hours;
	private Integer minutes = 0;
	private Task task;

	@Column(nullable = false, name = "hours")
	public Integer getHours() {
		return hours;
	}

	public void setHours(Integer hours) {
		this.hours = hours;
	}

	@Column(nullable = false, name = "minutes")
	public Integer getMinutes() {
		return minutes;
	}

	public void setMinutes(Integer minutes) {
		this.minutes = minutes;
	}

	@Column(name = "task_date", nullable = false)
	public Date getTaskDate() {
		return taskDate;
	}

	public void setTaskDate(Date taskDate) {
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
