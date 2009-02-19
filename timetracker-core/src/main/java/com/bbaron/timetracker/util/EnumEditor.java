package com.bbaron.timetracker.util;

import java.beans.PropertyEditorSupport;

import org.springframework.util.StringUtils;

@SuppressWarnings("unchecked")
public class EnumEditor extends PropertyEditorSupport {

	private Class enumType;
	private boolean allowEmpty;

	public EnumEditor(Class enumType) {
		this(enumType, true);
	}

	public EnumEditor(Class enumType, boolean allowEmpty) {
		this.enumType = enumType;
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
			setValue(Enum.valueOf(enumType, text));
		}
	}

	@Override
	public String getAsText() {
		Object value = getValue();
		return value == null ? "" : value.toString();
	}
}
