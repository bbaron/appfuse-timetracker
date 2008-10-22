package com.bbaron.timetracker.web.controllers.annotated;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;

import com.bbaron.timetracker.service.TimecardService;
import com.bbaron.timetracker.web.commands.NewTimecard;
import com.bbaron.timetracker.web.validators.NewTimecardValidator;

@Controller
@RequestMapping("/new-timecard-setup.htm")
public class NewTimecardSetupController extends AbstractTimecardController {

    @Autowired
    public NewTimecardSetupController(TimecardService timecardService, NewTimecardValidator validator) {
        super(timecardService, validator);
    }

    @RequestMapping(method = RequestMethod.GET)
    public String setupForm(@RequestParam(required = false, value = "submitterId") Long submitterId, ModelMap model) {
        NewTimecard timecard = new NewTimecard();
        timecard.setSubmitterId(submitterId);
        model.addAttribute("timecard", timecard);
        return "new-timecard-setup";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String processSubmit(@ModelAttribute("timecard") NewTimecard timecard, BindingResult result,
            SessionStatus status) {
        validator.validate(timecard, result);
        if (result.hasErrors()) {
            return "new-timecard-setup";
        } else {
            timecardService.createTimecard(timecard.getSubmitterId(), timecard.getStartDate());
            status.setComplete();
            return "redirect:timecard.htm";
        }
    }
}
