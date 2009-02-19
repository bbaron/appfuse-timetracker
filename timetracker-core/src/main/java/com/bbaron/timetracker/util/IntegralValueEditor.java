package com.bbaron.timetracker.util;

import java.beans.PropertyEditorSupport;

import org.springframework.util.StringUtils;

public abstract class IntegralValueEditor extends PropertyEditorSupport {

    private final boolean allowEmpty;
    
    public IntegralValueEditor(boolean allowEmpty) {
        this.allowEmpty = allowEmpty;
    }
    
    public IntegralValueEditor() {
        this(true);
    }
    

    @Override
    public String getAsText() {
        Object value = getValue();
        return value == null ? "" : value.toString();
    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        if (StringUtils.hasText(text) == false) {
            if (allowEmpty) {
                setValue(null);
            } else {
                throw new IllegalArgumentException(
                        "text is empty and allowEmpty is false");
            }
        } else {
            try {
                setValue(construct(Integer.parseInt(text)));
            } catch (Exception e) {
                throw new IllegalArgumentException(text + " is an invalid Hour value", e);
            }
        }
    }

    protected abstract Object construct(int n);

}
