package com.bbaron.timetracker.model;

import java.io.Serializable;

public interface Entity<PK extends Serializable> {

	void setId(PK id);
	PK getId();
}
