package com.bbaron.timetracker.web.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.mvc.SimpleFormController;

import com.bbaron.timetracker.model.Timecard;

public class NewTimecardSetupController extends SimpleFormController {

	@Override
	protected Object formBackingObject(HttpServletRequest request)
			throws Exception {
		return new Timecard();
	}

}
