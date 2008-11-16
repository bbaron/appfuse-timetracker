package com.bbaron.timetracker.util;

import java.beans.PropertyEditorSupport;

import org.joda.time.Hours;
import org.springframework.util.StringUtils;

public class HoursEditor extends PropertyEditorSupport {

	private boolean allowEmpty;

	public HoursEditor() {
		this(true);
	}

	public HoursEditor(boolean allowEmpty) {
		this.allowEmpty = allowEmpty;
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
                setValue(Hours.hours(Integer.parseInt(text)));
            } catch (Exception e) {
                throw new IllegalArgumentException(text + " is an invalid Hour value", e);
            }
		}
	}

	@Override
	public String getAsText() {
		Object value = getValue();
		return value == null ? "" : value.toString();
	}
}
