package com.bbaron.timetracker.model;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User implements Serializable {

	private static final long serialVersionUID = -275331587263329788L;
	private String firstName;
    private String lastName;
    private String username;
    private Boolean enabled = Boolean.TRUE;
    private String password = "changeme";

	User() {
    	
    }
    
    public User(String username) {
		this.username = username;
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

    @Column(length = 50)
    @Id
    public String getUsername() {
        return username;
    }

//    public String getId() {
//        return getUsername();
//    }
// 
    public void setUsername(String username) {
        this.username = username;
    }
    
//    public void setId(String id) {
//        setUsername(id);
//    }
//
    @Override
    public String toString() {
        return getUsername();
    }

    @Column(name = "enabled", nullable = false)
    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    @Column(name = "password", length = 50, nullable = false)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
