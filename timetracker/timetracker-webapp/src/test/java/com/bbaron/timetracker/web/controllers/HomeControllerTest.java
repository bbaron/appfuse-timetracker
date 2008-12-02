package com.bbaron.timetracker.web.controllers;

import static org.junit.Assert.*;

import javax.servlet.http.HttpServletRequest;

import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.bbaron.timetracker.model.Timecard;
import com.bbaron.timetracker.service.TimecardServiceImpl;

public class HomeControllerTest extends TimecardServiceImpl {

    private Timecard timecard = null;

    @Override
    public Timecard getLatestTimecard(String submitterUsername) {
        return timecard;
    }

    @Test
    public void handleRequest() throws Exception {
        HttpServletRequest request = new MockHttpServletRequest("GET", "/");
        HomeController controller = new HomeController();
        ModelAndView mav = controller.handleRequest(request, null);
        assertFalse(mav.getModel().containsKey("hasLatest"));
        
        controller.setTimecardService(this);
        mav = controller.handleRequest(request, null);
        assertEquals(Boolean.FALSE, mav.getModel().get("hasLatest"));
        
        timecard = new Timecard();
        mav = controller.handleRequest(request, null);
        assertEquals(Boolean.TRUE, mav.getModel().get("hasLatest"));
    }
}
