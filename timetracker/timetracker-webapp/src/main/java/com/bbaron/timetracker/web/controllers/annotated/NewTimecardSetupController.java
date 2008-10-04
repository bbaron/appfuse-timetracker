package com.bbaron.timetracker.web.controllers.annotated;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.bind.support.SessionStatus;

import com.bbaron.timetracker.service.TimecardService;
import com.bbaron.timetracker.web.commands.NewTimecard;
import com.bbaron.timetracker.web.validators.NewTimecardValidator;

@Controller
@RequestMapping("/new-timecard-setup.htm")
public class NewTimecardSetupController {

    private TimecardService timecardService;
    private NewTimecardValidator validator;

    @Autowired
    public NewTimecardSetupController(TimecardService timecardService, NewTimecardValidator validator) {
        this.timecardService = timecardService;
        this.validator = validator;
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
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
