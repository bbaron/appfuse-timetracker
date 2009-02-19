package com.bbaron.timetracker.model;

import java.io.Serializable;

public interface IEntity<PK extends Serializable> extends Serializable {

	void setId(PK id);
	PK getId();
}
