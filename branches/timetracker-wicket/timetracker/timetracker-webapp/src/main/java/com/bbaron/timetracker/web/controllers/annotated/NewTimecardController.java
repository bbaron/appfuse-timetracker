package com.bbaron.timetracker.web.controllers.annotated;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.bbaron.timetracker.web.commands.NewTimecard;
import com.bbaron.timetracker.web.validators.NewTimecardValidator;

@Controller
@RequestMapping("/timecard-new.htm")
@SessionAttributes("timecard")
public class NewTimecardController extends AbstractTimecardController {

    private NewTimecardValidator newTimecardValidator;

    @RequestMapping(method = RequestMethod.GET)
    public String setupNewTimecardForm(HttpServletRequest request, ModelMap model) {
        String submitter = request.getRemoteUser();
        NewTimecard timecard = new NewTimecard();
        timecard.setSubmitter(submitter);
        model.addAttribute("timecard", timecard);
        return "timecard-new";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String processNewTimecard(@ModelAttribute("timecard") NewTimecard timecard, BindingResult result,
            SessionStatus status) {
        this.newTimecardValidator.validate(timecard, result);
        if (result.hasErrors()) {
            return "timecard-new";
        } else {
            Long id = timecardService.createTimecard(timecard.getSubmitter(), timecard.getStartDate());
            status.setComplete();
            return "redirect:timecard-edit.htm?timecardId=" + id;
        }
    }

    public void setNewTimecardValidator(NewTimecardValidator newTimecardValidator) {
        this.newTimecardValidator = newTimecardValidator;
    }

}
