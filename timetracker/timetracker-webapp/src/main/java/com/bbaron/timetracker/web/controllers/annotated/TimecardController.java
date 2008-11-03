package com.bbaron.timetracker.web.controllers.annotated;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;

import org.apache.log4j.Logger;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.bbaron.timetracker.model.Task;
import com.bbaron.timetracker.model.TimeAllocation;
import com.bbaron.timetracker.model.Timecard;
import com.bbaron.timetracker.model.TimecardSearchCriteria;
import com.bbaron.timetracker.model.TimecardStatus;
import com.bbaron.timetracker.model.User;
import com.bbaron.timetracker.service.TimecardService;
import com.bbaron.timetracker.util.Constants;
import com.bbaron.timetracker.util.EnumEditor;
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
        SimpleDateFormat dateFormat = new SimpleDateFormat(Constants.SYSTEM_DATE_FORMAT);
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
        binder.registerCustomEditor(TimecardStatus.class, new EnumEditor(TimecardStatus.class));
        binder.registerCustomEditor(Task.class, new EnumEditor(Task.class));
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
        if (logger.isDebugEnabled()) {
            logger.debug("timecard has " + timecard.getTimeAllocationList().size() + " time allocations");
            for (TimeAllocation ta : timecard.getTimeAllocationList()) {
                logger.debug(ta);
            }
        }
        model.addAttribute("timecard", timecard);
        model.addAttribute("timeAllocation", new TimeAllocation());
        model.addAttribute("tasks", timecardService.getAllTasks());
        Map<Long, String> users = getAllUsers();

        model.addAttribute("users", users);
        model.addAttribute("dates", timecard.getDateSelection());
        model.addAttribute("statuses", timecardService.getAllStatuses());
        return "timecard-edit";
    }

    private Map<Long, String> getAllUsers() {
        Map<Long, String> users = new HashMap<Long, String>();

        for (User user : timecardService.getAllUsers()) {
            users.put(user.getId(), user.getUsername());
        }
        return users;
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
