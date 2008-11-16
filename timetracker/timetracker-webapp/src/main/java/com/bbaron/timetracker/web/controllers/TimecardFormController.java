package com.bbaron.timetracker.web.controllers;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractFormController;
import org.springframework.web.servlet.mvc.SimpleFormController;

import com.bbaron.timetracker.model.Timecard;
import com.bbaron.timetracker.service.TimecardService;

public class TimecardFormController extends AbstractFormController {

    private TimecardService timecardService;
    
    
    public TimecardFormController() {
        setCommandName("timeAllocation");
        setSessionForm(true);
    }


    public void setTimecardService(TimecardService timecardService) {
        this.timecardService = timecardService;
    }


    @Override
    protected Object formBackingObject(HttpServletRequest request) throws Exception {
        Long timecardId = ServletRequestUtils.getLongParameter(request, "timecardId");
        Long submitterId = ServletRequestUtils.getLongParameter(request, "submitterId");
        if (timecardId == null && submitterId == null) {
            throw new ServletRequestBindingException("one or timecardId, submitterId is required");
        }
        Timecard timecard = null;
        if (timecardId != null) {
            timecard = timecardService.getTimecard(timecardId);
        } else {
            timecard = timecardService.getLatestTimecard(submitterId);
            if (timecard == null) {
//                return setupNewTimecardForm(submitterId, model);
            }
        }
        return super.formBackingObject(request);
    }


    @Override
    protected ModelAndView processFormSubmission(HttpServletRequest request, HttpServletResponse response,
            Object command, BindException errors) throws Exception {
        // TODO Auto-generated method stub
        return null;
    }


    @Override
    protected ModelAndView showForm(HttpServletRequest request, HttpServletResponse response, BindException errors)
            throws Exception {
        // TODO Auto-generated method stub
        return showForm(request, errors, "timecard-edit", null);
    }

}
