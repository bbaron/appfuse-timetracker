package com.bbaron.timetracker.web.validators;

import org.springframework.validation.Errors;

import com.bbaron.timetracker.web.commands.TimecardEntry;


public class TimecardEntryValidator extends AbstractTimecardValidator {

    public TimecardEntryValidator() {
        super(TimecardEntry.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        // TODO Auto-generated method stub

    }

}
