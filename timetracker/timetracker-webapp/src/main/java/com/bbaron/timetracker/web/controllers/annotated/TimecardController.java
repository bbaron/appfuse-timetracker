package com.bbaron.timetracker.web.controllers.annotated;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import com.bbaron.timetracker.model.TimeAllocation;
import com.bbaron.timetracker.model.Timecard;
import com.bbaron.timetracker.web.validators.TimeAllocationValidator;
    
@Controller
@RequestMapping("/timecard-*.htm")
@SessionAttributes( { "timeAllocation", "timecard" })
public class TimecardController extends AbstractTimecardController {

    protected final Logger logger = Logger.getLogger(getClass());
    private TimeAllocationValidator timeAllocationValidator;

    public void setTimeAllocationValidator(TimeAllocationValidator timecardEntryValidator) {
        this.timeAllocationValidator = timecardEntryValidator;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/timecard-edit.htm")
    public String setupTimecardForm(@RequestParam(required = false, value = "timecardId") Long timecardId,
            ModelMap model, HttpServletRequest request) throws Exception {
        String submitter = request.getRemoteUser();
        logger.debug("remote user is " + submitter);
        if (timecardId == null && submitter == null) {
            throw new ServletException("either timecardId or submitterId must be specified");
        }
        Timecard timecard = null;
        if (timecardId != null) {
            timecard = timecardService.getTimecard(timecardId);
        } else {
            timecard = timecardService.getLatestTimecard(submitter);
            if (timecard == null) {
                return "redirect:timecard:new";
            }
        }
        model.addAttribute("timecard", timecard);
        model.addAttribute("timeAllocation", new TimeAllocation());
        model.addAttribute("tasks", timecardService.getAllTasks());

        model.addAttribute("users", getAllUsers());
        model.addAttribute("dates", timecard.getDateSelection());
        model.addAttribute("statuses", timecardService.getAllStatuses());
        return "timecard-edit";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/timecard-enter-time-allocation.htm")
    public String enterTimeAllocation(@RequestParam(required = true, value = "timecardId") Long timecardId,
            @ModelAttribute("timeAllocation") TimeAllocation alloc, BindingResult result, SessionStatus status) {
        logger.debug("enterTimeAllocation " + alloc);
        timeAllocationValidator.validate(alloc, result);
        if (!result.hasErrors()) {
            logger.debug("submitting alloc " + alloc + " for timecard id " + timecardId);
            timecardService.enterTimeAllocation(timecardId, alloc);
            status.setComplete();
        }
        return "redirect:timecard-edit.htm?" + "timecardId=" + timecardId;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/timecard-action.htm", 
            params = "timecardAction=Save")
    public String saveTimecard(@ModelAttribute("timecard") Timecard timecard, BindingResult result,
            SessionStatus status) {
        logger.debug("saveTimecard " + timecard);

        if (!result.hasErrors()) {
            timecardService.saveTimecard(timecard);
            status.setComplete();
        }
        return "redirect:timecard-edit.htm?" + "timecardId=" + timecard.getId();
    }

    
    @RequestMapping(method = RequestMethod.POST, value = "/timecard-action.htm", 
            params = "timecardAction=Submit")
    public String submitTimecard(@ModelAttribute("timecard") Timecard timecard, BindingResult result,
            SessionStatus status) {
        timecardService.submitTimecard(timecard);
        return "redirect:timecard-search.htm";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/timecard-action.htm", 
            params = "timecardAction=Delete")
    public String deleteTimecard(@ModelAttribute("timecard") Timecard timecard, BindingResult result,
            SessionStatus status) {
        timecardService.deleteTimecard(timecard.getId());
        return "redirect:timecard-search.htm";
    }


}
