package com.bbaron.timetracker.web.controllers.annotated;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import com.bbaron.timetracker.service.TimecardService;

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
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

}
