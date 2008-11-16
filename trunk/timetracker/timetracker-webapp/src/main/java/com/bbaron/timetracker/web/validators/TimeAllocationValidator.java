package com.bbaron.timetracker.web.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import com.bbaron.timetracker.model.TimeAllocation;


@Component
public class TimeAllocationValidator extends AbstractTimecardValidator {

    public TimeAllocationValidator() {
        super(TimeAllocation.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        rejectIfEmpty(target, errors, "task");
        rejectIfEmpty(target, errors, "taskDate");
        TimeAllocation alloc = (TimeAllocation) target;
        if (alloc.getHours() + alloc.getMinutes() <= 0) {
            errors.reject("time.entered.none", "no time allocated");
        }
        rejectIfOutOfBounds(target, errors, "hours", 0, 24, alloc.getHours());
        rejectIfOutOfBounds(target, errors, "hours", 0, 60, alloc.getMinutes());
    }

}
