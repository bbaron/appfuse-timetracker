package com.bbaron.timetracker.web.controllers.annotated;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;

import com.bbaron.timetracker.model.Timecard;
import com.bbaron.timetracker.model.TimecardSearchCriteria;
import com.bbaron.timetracker.web.validators.TimecardSearchCriteriaValidator;

@Controller
@RequestMapping("/timecard-search.htm")
public class TimecardSearchController extends AbstractTimecardController {

    public void setTimecardSearchCriteriaValidator(TimecardSearchCriteriaValidator v) {
        super.setValidator(v);
    }
    
    @RequestMapping(method = RequestMethod.POST)
    public String searchTimecards(@ModelAttribute("criteria") TimecardSearchCriteria criteria, BindingResult result,
            SessionStatus status, Model model) {
        logger.info("criteria = " + criteria);
        validator.validate(criteria, result);
        model.addAttribute("criteria", criteria);
        if (!result.hasErrors()) {
            performSearch(criteria, model);
        }
        return setupSearchTimecards(model, null);
    }

    private void performSearch(TimecardSearchCriteria criteria, Model model) {
        Collection<Timecard> timecards = timecardService.searchTimecards(criteria);
        logger.info("timecards found = " + timecards.size());
        model.addAttribute("timecards", timecards);
    }

    @RequestMapping(method = RequestMethod.GET)
    public String setupSearchTimecards(Model model, HttpServletRequest request) {
        TimecardSearchCriteria criteria = new TimecardSearchCriteria();
        if (request != null) {
            criteria.setSubmitter(request.getRemoteUser());
            performSearch(criteria, model);
        }
        model.addAttribute("criteria", criteria);
        model.addAttribute("users", getAllUsers());
        model.addAttribute("statuses", timecardService.getAllStatuses());
        return "timecard-search";
    }


}
