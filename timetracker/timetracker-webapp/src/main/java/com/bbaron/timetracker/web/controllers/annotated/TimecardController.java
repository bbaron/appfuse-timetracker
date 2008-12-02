package com.bbaron.timetracker.web.controllers.annotated;

import java.util.Collection;
import java.util.Map;

import javax.servlet.ServletException;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import com.bbaron.timetracker.model.*;
import com.bbaron.timetracker.service.TimecardService;
import com.bbaron.timetracker.util.*;
import com.bbaron.timetracker.web.commands.NewTimecard;
import com.bbaron.timetracker.web.validators.NewTimecardValidator;
import com.bbaron.timetracker.web.validators.TimeAllocationValidator;
import com.bbaron.timetracker.web.validators.TimecardSearchCriteriaValidator;

@Controller
@RequestMapping("/timecard-*.htm")
@SessionAttributes("timeAllocation")
public class TimecardController {

    protected final Logger logger = Logger.getLogger(getClass());
    private TimecardService timecardService;
    private NewTimecardValidator newTimecardValidator;
    private TimeAllocationValidator timeAllocationValidator;
    private TimecardSearchCriteriaValidator timecardSearchCriteriaValidator;

    public void setNewTimecardValidator(NewTimecardValidator newTimecardValidator) {
        this.newTimecardValidator = newTimecardValidator;
    }

    public void setTimeAllocationValidator(TimeAllocationValidator timecardEntryValidator) {
        this.timeAllocationValidator = timecardEntryValidator;
    }

    public void setTimecardSearchCriteriaValidator(TimecardSearchCriteriaValidator timecardSearchCriteriaValidator) {
        this.timecardSearchCriteriaValidator = timecardSearchCriteriaValidator;
    }

    public void setTimecardService(TimecardService timecardService) {
        this.timecardService = timecardService;
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        logger.debug("initializing web data binding");
        binder.registerCustomEditor(TimecardDate.class, new TimecardDateEditor(true));
        binder.registerCustomEditor(TimecardStatus.class, new EnumEditor(TimecardStatus.class));
        binder.registerCustomEditor(Task.class, new EnumEditor(Task.class));
        binder.registerCustomEditor(TimecardHours.class, new TimecardHoursEditor());
        binder.registerCustomEditor(TimecardMinutes.class, new TimecardMinutesEditor());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/timecard-edit.htm")
    public String setupTimecardForm(@RequestParam(required = false, value = "timecardId") Long timecardId,
            @RequestParam(required = false, value = "submitterId") Long submitterId, ModelMap model) throws Exception {
        if (timecardId == null && submitterId == null) {
            throw new ServletException("one or timecardId, submitterId is required");
        }
        Timecard timecard = null;
        if (timecardId != null) {
            timecard = timecardService.getTimecard(timecardId);
        } else {
            timecard = timecardService.getLatestTimecard(submitterId);
            if (timecard == null) {
                return setupNewTimecardForm(submitterId, model);
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
        return Utils.toMap(timecardService.getAllUsers(), "id", "username");
    }

    @RequestMapping(method = RequestMethod.POST, value = "/timecard-search.htm")
    public String searchTimecards(@ModelAttribute("criteria") TimecardSearchCriteria criteria, BindingResult result,
            SessionStatus status, ModelMap model) {
        logger.info("criteria = " + criteria);
        timecardSearchCriteriaValidator.validate(criteria, result);
        model.addAttribute("criteria", criteria);
        if (!result.hasErrors()) {
            Collection<Timecard> timecards = timecardService.searchTimecards(criteria);
            logger.info("timecards found = " + timecards.size());
            model.addAttribute("timecards", timecards);
        }
        return setupSearchTimecards(model);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/timecard-search.htm")
    public String setupSearchTimecards(ModelMap model) {
        model.addAttribute("criteria", new TimecardSearchCriteria());
        model.addAttribute("users", getAllUsers());
        model.addAttribute("statuses", timecardService.getAllStatuses());
        return "timecard-search";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/timecard-edit.htm")
    public String processTimecard(@RequestParam(required = true, value = "timecardId") Long timecardId,
            @ModelAttribute("timeAllocation") TimeAllocation alloc, BindingResult result, SessionStatus status) {
        timeAllocationValidator.validate(alloc, result);
        if (result.hasErrors()) {
            return "redirect:timecard-edit.htm?" + "timecardId=" + timecardId;
        } else {
            logger.debug("submitting alloc " + alloc + " for timecard id " + timecardId);
            timecardService.enterTimeAllocation(timecardId, alloc);
            status.setComplete();
            return "redirect:timecard-edit.htm?" + "timecardId=" + timecardId;
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "/timecard-new.htm")
    public String setupNewTimecardForm(@RequestParam(required = false, value = "submitterId") Long submitterId,
            ModelMap model) {
        NewTimecard timecard = new NewTimecard();
        timecard.setSubmitterId(submitterId);
        model.addAttribute("timecard", timecard);
        return "timecard-new";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/timecard-submit.htm")
    public String submitTimecard(@RequestParam(required = true, value = "timecardId") Long timecardId) {
        logger.debug("submitting timecard " + timecardId);
        timecardService.submitTimecard(timecardId);
        return "redirect:timecard-edit.htm?timecardId=" + timecardId;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/timecard-new.htm")
    public String processNewTimecard(@ModelAttribute("timecard") NewTimecard timecard, BindingResult result,
            SessionStatus status) {
        newTimecardValidator.validate(timecard, result);
        if (result.hasErrors()) {
            return "timecard-new";
        } else {
            Long id = timecardService.createTimecard(timecard.getSubmitterId(), timecard.getStartDate());
            status.setComplete();
            return "redirect:timecard-edit.htm?timecardId=" + id;
        }
    }

}
