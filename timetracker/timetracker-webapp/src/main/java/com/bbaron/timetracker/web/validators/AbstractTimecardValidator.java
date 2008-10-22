package com.bbaron.timetracker.web.validators;

import org.springframework.validation.Validator;

public abstract class AbstractTimecardValidator implements Validator {

    private Class<?> supportedClass;

    public AbstractTimecardValidator(Class<?> supportedClass) {
        this.supportedClass = supportedClass;
    }

    @Override
    public boolean supports(@SuppressWarnings("unchecked") Class clazz) {
        return supportedClass.isAssignableFrom(clazz);
    }

}