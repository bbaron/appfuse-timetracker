package com.bbaron.timetracker.web.controllers.annotated;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import com.bbaron.timetracker.model.Timecard;
import com.bbaron.timetracker.service.TimecardService;
import com.bbaron.timetracker.web.commands.TimecardEntry;
import com.bbaron.timetracker.web.validators.TimecardEntryValidator;

@Controller
@RequestMapping("/timecard.htm")
@SessionAttributes("timecardEntry")
public class TimecardController extends AbstractTimecardController {

    @Autowired
    public TimecardController(TimecardService timecardService, TimecardEntryValidator validator) {
        super(timecardService, validator);
    }
    
    @RequestMapping(method = RequestMethod.GET)
    public String setupForm(@RequestParam(required = true, value = "timecardId") Long timecardId, ModelMap model) {
        Timecard timecard = timecardService.getTimecard(timecardId);
        model.addAttribute("timecard", timecard);
        model.addAttribute("timecardEntry", new TimecardEntry());
        model.addAttribute("tasks", timecardService.getAllTasks());
        model.addAttribute("users", timecardService.getAllUsers());
        model.addAttribute("statuses", timecardService.getAllStatuses());
        return "timecard";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String processSubmit(@ModelAttribute("timecardEntry") TimecardEntry timecardEntry, BindingResult result,
            SessionStatus status) {
        validator.validate(timecardEntry, result);
        if (result.hasErrors()) {
            return "timecard";
        } else {
//            timecardService.enterTimeAllocation(timecardEntry.getTimecardId(), timecardEntry.getTimeAllocation());
            status.setComplete();
            return "redirect:timecard.htm";
        }
    }

}
