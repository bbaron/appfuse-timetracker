package com.bbaron.timetracker.util;

import java.beans.PropertyEditorSupport;

import org.springframework.util.StringUtils;

import com.bbaron.timetracker.model.TimecardDate;

public class TimecardDateEditor extends PropertyEditorSupport {

    private final String dateFormat;

    private final boolean allowEmpty;

    public TimecardDateEditor(boolean allowEmpty, String dateFormat) {
        super();
        this.dateFormat = dateFormat;
        this.allowEmpty = allowEmpty;
    }

    public TimecardDateEditor(boolean allowEmpty) {
        this(allowEmpty, Constants.SYSTEM_DATE_FORMAT);
    }

    public TimecardDateEditor() {
        this(true);
    }

    @Override
    public String getAsText() {
        TimecardDate value = (TimecardDate) getValue();
        return (value != null ? value.toString(dateFormat) : "");
    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        if (this.allowEmpty && !StringUtils.hasText(text)) {
            setValue(null);
        } else {
            try {
                setValue(TimecardDate.date(text, dateFormat));
            } catch (IllegalArgumentException e) {
                throw e;
            } catch (Exception e) {
                throw new IllegalArgumentException("Could not parse date: " + e.toString(), e);
            }
        }
    }

}
