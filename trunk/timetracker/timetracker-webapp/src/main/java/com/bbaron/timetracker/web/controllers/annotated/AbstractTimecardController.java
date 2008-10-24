package com.bbaron.timetracker.web.controllers.annotated;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import com.bbaron.timetracker.model.Task;
import com.bbaron.timetracker.model.TimecardStatus;
import com.bbaron.timetracker.service.TimecardService;
import com.bbaron.timetracker.util.EnumEditor;

public abstract class AbstractTimecardController {
    protected final TimecardService timecardService;
    protected final Validator validator;
    protected final Logger logger = Logger.getLogger(getClass());

    public AbstractTimecardController(TimecardService timecardService, Validator validator) {
        this.timecardService = timecardService;
        this.validator = validator;
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        logger.debug("initializing web data binding");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat timeFormat = new SimpleDateFormat("h:m a");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, "startDate", new CustomDateEditor(dateFormat, true));
        binder.registerCustomEditor(Date.class, "date", new CustomDateEditor(dateFormat, true));
        binder.registerCustomEditor(Date.class, "start", new CustomDateEditor(timeFormat, true));
        binder.registerCustomEditor(Date.class, "end", new CustomDateEditor(timeFormat, true));
        binder.registerCustomEditor(TimecardStatus.class, new EnumEditor(TimecardStatus.class));
        binder.registerCustomEditor(Task.class, new EnumEditor(Task.class));
    }

}
