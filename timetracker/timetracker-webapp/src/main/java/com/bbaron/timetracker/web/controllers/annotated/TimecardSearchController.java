package com.bbaron.timetracker.web.controllers.annotated;

import java.util.Collection;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
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
            SessionStatus status, ModelMap model) {
        logger.info("criteria = " + criteria);
        validator.validate(criteria, result);
        model.addAttribute("criteria", criteria);
        if (!result.hasErrors()) {
            Collection<Timecard> timecards = timecardService.searchTimecards(criteria);
            logger.info("timecards found = " + timecards.size());
            model.addAttribute("timecards", timecards);
        }
        return setupSearchTimecards(model);
    }

    @RequestMapping(method = RequestMethod.GET)
    public String setupSearchTimecards(ModelMap model) {
        model.addAttribute("criteria", new TimecardSearchCriteria());
        model.addAttribute("users", getAllUsers());
        model.addAttribute("statuses", timecardService.getAllStatuses());
        return "timecard-search";
    }


}
