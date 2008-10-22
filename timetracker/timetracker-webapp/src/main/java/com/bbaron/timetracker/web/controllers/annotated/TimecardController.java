package com.bbaron.timetracker.web.controllers.annotated;

import java.util.Collection;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;

import com.bbaron.timetracker.model.Timecard;
import com.bbaron.timetracker.service.TimecardService;
import com.bbaron.timetracker.web.commands.TimecardEntry;

@Controller
@RequestMapping("/timecard.htm")
public class TimecardController extends AbstractTimecardController {

    @Autowired
    public TimecardController(TimecardService timecardService, Validator validator) {
        super(timecardService, validator);
    }
    
    @ModelAttribute("users")
    public Map<Long, String> getUsers() {
        return timecardService.getAllUsers();
    }
    
    @ModelAttribute("statuses")
    public Collection<String> getStatuses() {
        return timecardService.getAllStatuses();
    }

    @ModelAttribute("tasks")
    public Collection<String> getTasks() {
        return timecardService.getAllTasks();
    }

    @RequestMapping(method = RequestMethod.GET)
    public String setupForm(@RequestParam(required = true, value = "timecardId") Long timecardId, ModelMap model) {
        Timecard timecard = timecardService.getTimecard(timecardId);
        model.addAttribute("timecard", timecard);
        return "timecard";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String processSubmit(@ModelAttribute("timecardEntry") TimecardEntry timecardEntry, BindingResult result,
            SessionStatus status) {
        validator.validate(timecardEntry, result);
        if (result.hasErrors()) {
            return "timecard";
        } else {
            timecardService.enterTimeAllocation(timecardEntry.getTimecardId(), timecardEntry.getTimeAllocation());
            status.setComplete();
            return "redirect:timecard.htm";
        }
    }

}
