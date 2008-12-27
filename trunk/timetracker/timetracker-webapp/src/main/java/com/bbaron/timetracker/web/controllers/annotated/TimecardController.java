package com.bbaron.timetracker.web.controllers.annotated;

import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import com.bbaron.timetracker.model.Task;
import com.bbaron.timetracker.model.TimeAllocation;
import com.bbaron.timetracker.model.Timecard;
import com.bbaron.timetracker.model.TimecardStatus;
import com.bbaron.timetracker.service.TimecardService;
import com.bbaron.timetracker.temporal.TimecardDate;
import com.bbaron.timetracker.temporal.TimecardHours;
import com.bbaron.timetracker.temporal.TimecardMinutes;
import com.bbaron.timetracker.util.*;
import com.bbaron.timetracker.web.validators.TimeAllocationValidator;
    
@Controller
@RequestMapping("/timecard-*.htm")
@SessionAttributes( { "timeAllocation", "timecard" })
public class TimecardController {

    protected final Logger logger = Logger.getLogger(getClass());
    private TimecardService timecardService;
    private TimeAllocationValidator timeAllocationValidator;

    public void setTimeAllocationValidator(TimeAllocationValidator timecardEntryValidator) {
        this.timeAllocationValidator = timecardEntryValidator;
    }

    public void setTimecardService(TimecardService timecardService) {
        this.timecardService = timecardService;
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(TimecardDate.class, new TimecardDateEditor(true));
        binder.registerCustomEditor(TimecardStatus.class, new EnumEditor(TimecardStatus.class));
        binder.registerCustomEditor(Task.class, new EnumEditor(Task.class));
        binder.registerCustomEditor(TimecardHours.class, new TimecardHoursEditor());
        binder.registerCustomEditor(TimecardMinutes.class, new TimecardMinutesEditor());
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

    private Map<String, String> getAllUsers() {
        return Utils.toMap(timecardService.getAllUsers(), "username", "username");
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

    @RequestMapping(method = RequestMethod.POST, value = "/timecard-save.htm")
    public String saveTimecard(@ModelAttribute("timecard") Timecard timecard, BindingResult result,
            SessionStatus status) {
        logger.debug("saveTimecard " + timecard);

        if (!result.hasErrors()) {
            timecardService.saveTimecard(timecard);
        }
        return "redirect:timecard-edit.htm?" + "timecardId=" + timecard.getId();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/timecard-submit.htm")
    public String submitTimecard(@RequestParam(required = true, value = "timecardId") Long timecardId) {
        logger.debug("submitting timecard " + timecardId);
        timecardService.submitTimecard(timecardId);
        return "redirect:timecard-edit.htm?timecardId=" + timecardId;
    }


}
