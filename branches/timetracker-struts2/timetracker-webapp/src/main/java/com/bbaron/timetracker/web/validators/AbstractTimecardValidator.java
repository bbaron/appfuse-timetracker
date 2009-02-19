package com.bbaron.timetracker.web.validators;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
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

    protected void rejectIfEmpty(Object target, Errors errors, String field) {
        ValidationUtils.rejectIfEmpty(errors, field, "field.required", new Object[] { field });
    }

    protected void rejectIfOutOfBounds(Object target, Errors errors, String field, Number min, Number max, Number value) {
        if (value == null) {
            rejectIfEmpty(target, errors, field);
            return;
        }
        if (value.longValue() < min.longValue() || value.longValue() > max.longValue()) {
            errors.reject("field.bound", new Object[] { field, min, max }, "value out of bounds");            
        }
    }
}