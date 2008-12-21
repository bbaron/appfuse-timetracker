package com.bbaron.timetracker.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "authorities")
public class Role implements IEntity<Long> {

	private static final long serialVersionUID = -275331587263329788L;
    private String authority;
    private String username;
    private Long id;

	public Role() {
    	
    }
    
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(nullable = false, length = 10)
    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    @Column(nullable = false, length = 50 )
    public String getUsername() {
        return username;
    }

    @Override
    public String toString() {
        return getUsername();
    }

    public void setUsername(String username) {
        this.username = username;
    }

}
