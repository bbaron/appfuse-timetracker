package com.bbaron.timetracker.web.controllers.annotated;

import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import com.bbaron.timetracker.model.Timecard;

@Controller
@RequestMapping("/timecard-approve.htm")
@SessionAttributes("timecard")
public class ApproveTimecardController extends AbstractTimecardController {

    @RequestMapping(method = RequestMethod.GET)
    public String setupApproveTimecardForm(@RequestParam(required = false, value = "timecardId") Long timecardId,
            Model model, HttpServletRequest request) {
        Collection<Timecard> submittedTimecards = timecardService.getSubmittedTimecards(request.getRemoteUser());
        if (submittedTimecards.isEmpty()) {
            return "redirect:home.htm";
        }
        model.addAttribute("submittedTimecards", submittedTimecards);
        if (timecardId == null) {
            timecardId = submittedTimecards.iterator().next().getId();
        }
        Timecard timecard = timecardService.getTimecard(timecardId);
        model.addAttribute("timecard", timecard);
        logger.debug("approve timecard model " + model.toString());
        return "timecard-approve";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String approveTimecard(@RequestParam(value = "timecardAction") String timecardAction,
            @ModelAttribute("timecard") Timecard timecard, 
            BindingResult result, SessionStatus status, HttpServletRequest request) throws ServletException {
        if (result.hasErrors()) {
            return "timecard-approve";
        } else {
            if (timecardAction.equals("Approve")) {
                timecardService.approveTimecard(timecard, request.getRemoteUser());                
            } else if (timecardAction.equals("Reject")) {
                timecardService.rejectTimecard(timecard);
            } else {
                throw new ServletException(timecardAction + ": unknown action");
            }
            status.setComplete();
            return "redirect:timecard-approve.htm";
        }
    }

}
