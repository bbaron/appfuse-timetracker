package com.bbaron.timetracker.web.controllers.annotated;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.bbaron.timetracker.model.TimeAllocation;
import com.bbaron.timetracker.model.Timecard;
import com.bbaron.timetracker.service.TimecardService;
import com.bbaron.timetracker.web.validators.TimecardEntryValidator;

@Controller
@RequestMapping("/timecard.htm")
@SessionAttributes("timeAllocation")
public class TimecardController extends AbstractTimecardController {

    @Autowired
    public TimecardController(TimecardService timecardService, TimecardEntryValidator validator) {
        super(timecardService, validator);
    }
    
    @RequestMapping(method = RequestMethod.GET)
    public String setupForm(@RequestParam(required = true, value = "timecardId") Long timecardId, ModelMap model) {
        Timecard timecard = timecardService.getTimecardDetail(timecardId);
        if (logger.isDebugEnabled()) {
        	logger.debug("timecard has " + timecard.getTimeAllocationList().size() + " time allocations");
        	for (TimeAllocation ta : timecard.getTimeAllocationList()) {
                logger.debug(ta);        	
			}
        }
        model.addAttribute("timecard", timecard);
        model.addAttribute("timeAllocation", new TimeAllocation());
        model.addAttribute("tasks", timecardService.getAllTasks());
        model.addAttribute("users", timecardService.getAllUsers());
        model.addAttribute("dates", timecard.getDateSelection());
        model.addAttribute("statuses", timecardService.getAllStatuses());
        return "timecard";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String processSubmit(
    		@RequestParam(required = true, value = "timecardId") Long timecardId,
    		@ModelAttribute("timeAllocation") TimeAllocation alloc,
    		BindingResult result,
            SessionStatus status) {
        validator.validate(alloc, result);
        if (result.hasErrors()) {
            return "redirect:timecard.htm?" + "timecardId=" + timecardId;
        } else {
        	logger.debug("submitting alloc " + alloc + " for timecard id " + timecardId);
            timecardService.enterTimeAllocation(timecardId, alloc);
            status.setComplete();
            return "redirect:timecard.htm?" + "timecardId=" + timecardId;
        }
    }

}
