package com.bbaron.timetracker.web.controllers;

import static org.junit.Assert.*;

import javax.servlet.http.HttpServletRequest;

import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import org.junit.Test;

public class HomeControllerTest {

	@Test
	public void handleRequest() throws Exception {
		HomeController controller = new HomeController();
		controller.setViewName("home");
		HttpServletRequest request = new MockHttpServletRequest("GET", "/");
		ModelAndView mav = controller.handleRequest(request, null);
		assertEquals("home", mav.getViewName());
	}
}
