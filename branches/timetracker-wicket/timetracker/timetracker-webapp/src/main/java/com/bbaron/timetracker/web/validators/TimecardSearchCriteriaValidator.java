package com.bbaron.timetracker.web.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.bbaron.timetracker.model.TimecardSearchCriteria;


@Component
public class TimecardSearchCriteriaValidator extends AbstractTimecardValidator implements Validator {

    public TimecardSearchCriteriaValidator() {
        super(TimecardSearchCriteria.class);
    }
    @Override
    public void validate(Object target, Errors errors) {
        // TODO Auto-generated method stub

    }

}
