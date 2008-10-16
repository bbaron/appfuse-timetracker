package com.bbaron.timetracker.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

@Entity
@Table(name = "tt_timecard")
public class Timecard implements IEntity<Long> {

	private Long id;
	private String status;
	private Date startDate;
	private String comments;
	private User submitter;
	private User approver;

    @ManyToOne(optional = false)
    public User getSubmitter() {
        return submitter;
    }

    public void setSubmitter(User submitter) {
        this.submitter = submitter;
    }

    @ManyToOne(optional = true)
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

    @Column(name = "status", length = 20, nullable = false)
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Column(name = "start_date", nullable = false)
    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    @Column(name = "comments", length = 255, nullable = true)
    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }


    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 0;
        User u = getSubmitter();
        result = prime * result
                + ((startDate == null) ? 0 : startDate.hashCode());
        result = prime * result
                + ((u == null) ? 0 : u.hashCode());
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
        ToStringBuilder sb = new ToStringBuilder(this, ToStringStyle.DEFAULT_STYLE)
        .append("submitter", this.getSubmitter().getUsername())
        .append("status", this.status)
        .append("startDate", this.startDate);
        return sb.toString();
    }

}
