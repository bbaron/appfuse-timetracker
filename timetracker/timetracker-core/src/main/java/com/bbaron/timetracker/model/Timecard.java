package com.bbaron.timetracker.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.*;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.joda.time.Duration;
import org.joda.time.MutablePeriod;
import org.joda.time.Period;

import com.bbaron.timetracker.temporal.TimecardDate;
import com.bbaron.timetracker.temporal.TimecardDuration;
import com.bbaron.timetracker.util.Constants;

@Entity
@Table(name = "tt_timecard", uniqueConstraints = { @UniqueConstraint(columnNames = {
		"submitter_id", "start_date" }) })
@NamedQueries( { 
	@NamedQuery(name = "latestTimecard", query = "from Timecard timecard "
			+ "join fetch timecard.submitter "
			+ "left join fetch timecard.approver "
			+ "left join fetch timecard.timeAllocations "
			+ "where timecard.submitter.id = :submitterId "
			+ "and timecard.startDate = (select max(t.startDate) " +
				"from Timecard t " +
				"where t.submitter.id = :submitterId)") 
	,@NamedQuery(name = "timecard", query = "from Timecard timecard "
			+ "join fetch timecard.submitter "
			+ "left join fetch timecard.approver "
			+ "left join fetch timecard.timeAllocations "
			+ "where timecard.id = :timecardId ")
    ,@NamedQuery(name = "submittedTimecards", query = "from Timecard timecard "
	            + "join fetch timecard.submitter "
	            + "left join fetch timecard.approver "
	            + "join fetch timecard.timeAllocations "
	            + "where timecard.submitter.id != :approverId "
	            + "and timecard.status = 'Submitted'")
	})
public class Timecard implements IEntity<Long> {

	private static final long serialVersionUID = -6182443930278749700L;
	private Long id;
	private TimecardStatus status = TimecardStatus.Draft;
	private TimecardDate startDate;
	private String comments = "No comment";
	private User submitter;
	private User approver;
	private List<TimeAllocation> timeAllocations = new ArrayList<TimeAllocation>();

	@org.hibernate.annotations.CollectionOfElements(fetch = FetchType.LAZY)
	@org.hibernate.annotations.IndexColumn(name = "position", nullable = false)
	@JoinTable(name = "tt_timecard_alloc", joinColumns = @JoinColumn(name = "timecard_id"))
    @org.hibernate.annotations.AccessType("field")
	public List<TimeAllocation> getTimeAllocations() {
		return timeAllocations;
	}
	
	@Transient
	public Collection<TimeAllocation> getTimeAllocationList() {
		return new ArrayList<TimeAllocation>(getTimeAllocations());
	}

	void setTimeAllocations(List<TimeAllocation> timeAllocations) {
		this.timeAllocations = timeAllocations;
	}

	public void addTimeAllocation(TimeAllocation alloc) {
		timeAllocations.add(alloc);
	}

	@ManyToOne(optional = false)
    @JoinColumn(name = "submitter_id")
	public User getSubmitter() {
		return submitter;
	}

	public void setSubmitter(User submitter) {
		this.submitter = submitter;
	}

	@ManyToOne(optional = true, fetch = FetchType.LAZY)
	@JoinColumn(name = "approver_id")
	public User getApprover() {
		return approver;
	}

	public void setApprover(User approver) {
		this.approver = approver;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "status", nullable = false, length = 20)
	public TimecardStatus getStatus() {
		return status;
	}

	public void setStatus(TimecardStatus status) {
		this.status = status;
	}

	@org.hibernate.annotations.Type(type = "com.bbaron.timetracker.model.hibernate.TimecardDateUserType")
	@Column(name = "start_date", nullable = false)
	public TimecardDate getStartDate() {
		return startDate;
	}

	public void setStartDate(TimecardDate startDate) {
		this.startDate = startDate;
	}

	@Column(name = "comments", length = 255, nullable = true)
	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}
	
	@Transient
	public Duration getDuration() {
	    Duration duration = Duration.ZERO;
	    for (TimeAllocation alloc : timeAllocations) {
            duration = duration.plus(alloc.getDuration());
        }
	    return duration;
	}
	
	public Period getPeriod() {
	    MutablePeriod period = new MutablePeriod();
        for (TimeAllocation alloc : timeAllocations) {
            period.add(alloc.getDuration());
        }	    
	    return new Period(period);
	}
	
	@Transient
	public boolean isUpdatable() {
	    return status.isUpdatable();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 0;
		User u = getSubmitter();
		result = prime * result
				+ ((startDate == null) ? 0 : startDate.hashCode());
		result = prime * result + ((u == null) ? 0 : u.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj instanceof Timecard == false)
			return false;
		Timecard other = (Timecard) obj;
		if (startDate == null) {
			if (other.startDate != null)
				return false;
		} else if (!startDate.equals(other.startDate))
			return false;
		User u1 = getSubmitter();
		User u2 = other.getSubmitter();
		if (u1 == null) {
			if (u2 != null)
				return false;
		} else if (!u1.equals(u2))
			return false;
		return true;
	}

	@Override
	public String toString() {
		ToStringBuilder sb = new ToStringBuilder(this,
				ToStringStyle.DEFAULT_STYLE).append("submitter",
				this.getSubmitter())
				.append("status", this.status).append("startDate",
						this.startDate);
		return sb.toString();
	}

	@Transient
	public String[] getDateSelection() {
	    TimecardDate startDate = getStartDate();
	    
        String[] dates = new String[7];
        TimecardDate next = startDate;
        for (int i = 0; i < 7; i++) {
            dates[i] = next.toString(Constants.SYSTEM_DATE_FORMAT);
            next = next.plusDays(1);
        }
		return dates;
	}

}
