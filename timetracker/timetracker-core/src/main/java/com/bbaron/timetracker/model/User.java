package com.bbaron.timetracker.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.AccessType;

@Entity
@Table(name = "tt_user")
public class User implements IEntity<Long> {

	private static final long serialVersionUID = -275331587263329788L;
	private String firstName;
    private String lastName;
    private String username;
    private Long id;

    @SuppressWarnings("unused")
	private User() {
    	
    }
    
    public User(String username) {
		this.username = username;
	}

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "first_name", length = 30, nullable = false)
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Column(name = "last_name", length = 30, nullable = false)
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Column(nullable = false, length = 50, unique = true, updatable = false)
    @AccessType("field")
    public String getUsername() {
        return username;
    }

    @Override
    public String toString() {
        return getUsername();
    }

}
