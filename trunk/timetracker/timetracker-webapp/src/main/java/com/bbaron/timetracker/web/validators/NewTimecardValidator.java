package com.bbaron.timetracker.web.validators;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.bbaron.timetracker.web.commands.NewTimecard;

public class NewTimecardValidator implements Validator {

    @Override
    public boolean supports(@SuppressWarnings("unchecked") Class clazz) {
        return NewTimecard.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        // TODO Auto-generated method stub

    }

}
