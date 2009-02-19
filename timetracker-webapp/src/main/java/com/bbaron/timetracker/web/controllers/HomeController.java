package com.bbaron.timetracker.web.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.ParameterizableViewController;

import com.bbaron.timetracker.service.TimecardService;

@Controller
public class HomeController extends ParameterizableViewController {

    protected Logger logger = Logger.getLogger(getClass());

    private TimecardService timecardService;

    @Override
    protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        ModelAndView mav = super.handleRequestInternal(request, response);
        logger.debug("view name = " + mav.getViewName());
        if (timecardService != null) {
            String user = request.getRemoteUser();
            if (user == null) {
                user = "nbhatia";
            }
            logger.debug("checking " + user + " has a latest timecard");
            mav.addObject("hasLatest", timecardService.getLatestTimecard(user) != null);
        } else {
            logger.warn("timecardService is null");
        }
        return mav;
    }

    @Autowired
    public void setTimecardService(TimecardService timecardService) {
        this.timecardService = timecardService;
    }

}
