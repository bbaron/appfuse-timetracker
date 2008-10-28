package com.bbaron.timetracker.web.validators;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.bbaron.timetracker.web.commands.NewTimecard;


public class NewTimecardValidator extends AbstractTimecardValidator implements Validator {

    public NewTimecardValidator() {
        super(NewTimecard.class);
    }
    @Override
    public void validate(Object target, Errors errors) {
        // TODO Auto-generated method stub

    }

}