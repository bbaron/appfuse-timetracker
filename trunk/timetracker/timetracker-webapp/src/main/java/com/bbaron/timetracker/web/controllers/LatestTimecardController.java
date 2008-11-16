package com.bbaron.timetracker.web.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import com.bbaron.timetracker.service.TimecardService;

public class LatestTimecardController extends AbstractController {

    private TimecardService timecardService;
    
    public void setTimecardService(TimecardService timecardService) {
        this.timecardService = timecardService;
    }

    @Override
    protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        Long submitterId = ServletRequestUtils.getRequiredLongParameter(request, "submitterId");
        return null;
    }

}
