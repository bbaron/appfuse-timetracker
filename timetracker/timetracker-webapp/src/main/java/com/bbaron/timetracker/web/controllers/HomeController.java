package com.bbaron.timetracker.web.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.ParameterizableViewController;

import com.bbaron.timetracker.service.TimecardService;

public class HomeController extends ParameterizableViewController {

    protected Logger logger = Logger.getLogger(getClass());

    private TimecardService timecardService;

    @Override
    protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        ModelAndView mav = super.handleRequestInternal(request, response);
        if (timecardService != null) {
            String user = request.getRemoteUser();
            if (user == null) {
                user = "nbhatia";
            }
            mav.addObject("hasLatest", timecardService.getLatestTimecard(user) != null);
        }
        return mav;
    }

    public void setTimecardService(TimecardService timecardService) {
        this.timecardService = timecardService;
    }

}
