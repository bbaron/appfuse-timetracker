package com.bbaron.timetracker.web.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import com.bbaron.timetracker.model.TimeAllocation;


@Component("timeAllocationValidator")
public class TimeAllocationValidator extends AbstractTimecardValidator {

    public TimeAllocationValidator() {
        super(TimeAllocation.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        rejectIfEmpty(target, errors, "task");
        rejectIfEmpty(target, errors, "taskDate");
        TimeAllocation alloc = (TimeAllocation) target;
        rejectIfOutOfBounds(target, errors, "hours", 0, 24, alloc.getHours());
        rejectIfOutOfBounds(target, errors, "hours", 0, 60, alloc.getMinutes());
    }

}
