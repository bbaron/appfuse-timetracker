package com.bbaron.timetracker.util;

import java.beans.PropertyEditorSupport;

import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.util.StringUtils;

public class LocalDateEditor extends PropertyEditorSupport {

    private final DateTimeFormatter dateFormat;

    private final boolean allowEmpty;

    public LocalDateEditor(DateTimeFormatter dateFormat, boolean allowEmpty) {
        super();
        this.dateFormat = dateFormat;
        this.allowEmpty = allowEmpty;
    }

    @Override
    public String getAsText() {
        LocalDate value = (LocalDate) getValue();
        return (value != null ? value.toString(dateFormat) : "");
    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        if (this.allowEmpty && !StringUtils.hasText(text)) {
            setValue(null);
        } else {
            try {
                setValue(this.dateFormat.parseDateTime(text).toLocalDate());
            } catch (IllegalArgumentException e) {
                throw e;
            } catch (Exception e) {
                throw new IllegalArgumentException("Could not parse date: " + e.toString(), e);
            }
        }
    }

}
