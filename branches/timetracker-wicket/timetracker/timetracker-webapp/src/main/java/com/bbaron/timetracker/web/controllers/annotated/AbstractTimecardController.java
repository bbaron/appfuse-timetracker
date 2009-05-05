package com.bbaron.timetracker.web.controllers.annotated;

import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import com.bbaron.timetracker.model.Task;
import com.bbaron.timetracker.model.TimecardStatus;
import com.bbaron.timetracker.service.TimecardService;
import com.bbaron.timetracker.temporal.TimecardDate;
import com.bbaron.timetracker.temporal.TimecardHours;
import com.bbaron.timetracker.temporal.TimecardMinutes;
import com.bbaron.timetracker.util.*;

public abstract class AbstractTimecardController {
	protected TimecardService timecardService;
    protected final Logger logger = Logger.getLogger(getClass());
        
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(TimecardDate.class, new TimecardDateEditor(true));
        binder.registerCustomEditor(TimecardStatus.class, new EnumEditor(TimecardStatus.class));
        binder.registerCustomEditor(Task.class, new EnumEditor(Task.class));
        binder.registerCustomEditor(TimecardHours.class, new TimecardHoursEditor());
        binder.registerCustomEditor(TimecardMinutes.class, new TimecardMinutesEditor());
    }

    public void setTimecardService(TimecardService timecardService) {
        this.timecardService = timecardService;
    }

    protected final Map<String, String> getAllUsers() {
        return Utils.toMap(timecardService.getAllUsers(), "username", "username");
    }

}
