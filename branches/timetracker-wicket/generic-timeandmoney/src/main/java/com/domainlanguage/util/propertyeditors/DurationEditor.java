package com.domainlanguage.util.propertyeditors;

import java.beans.PropertyEditorSupport;

import com.domainlanguage.time.TimeUnit;
import com.domainlanguage.time.Duration;
import com.domainlanguage.util.StringUtils;

/**
 * Property editor for {@linkplain Duration}.
 * 
 * <p>
 * In web MVC code, this editor will typically be registered with
 * <code>binder.registerCustomEditor</code> calls in a custom
 * <code>initBinder</code> method.
 * 
 */
public class DurationEditor extends PropertyEditorSupport {
    private final TimeUnit timeUnit;

    private final boolean allowEmpty;

    /**
     * Create a new DurationEditor instance, using the given
     * {@linkplain TimeUnit}.
     * <p>
     * The "allowEmpty" parameter states if an empty String should be allowed
     * for parsing, i.e. get interpreted as null value. Otherwise, an
     * IllegalArgumentException gets thrown in that case.
     * <p>
     * 
     * @param timeUnit
     *            TimeUnit to use
     * @param allowEmpty
     *            if empty strings should be allowed
     */
    public DurationEditor(TimeUnit timeUnit, boolean allowEmpty) {
        super();
        this.timeUnit = timeUnit;
        this.allowEmpty = allowEmpty;
    }

    /**
     * Parse the Date from the given text, using the specified TimeUnit.
     */
    public void setAsText(String text) throws IllegalArgumentException {
        if (this.allowEmpty && !StringUtils.hasText(text)) {
            // Treat empty String as null value.
            setValue(null);
        } else {
            try {
                long quantity = Long.parseLong(text);
                Duration duration = new Duration(quantity, timeUnit);
                setValue(duration);
            } catch (NumberFormatException ex) {
                IllegalArgumentException iae = new IllegalArgumentException("Could not parse text: " + 
                        ex.getMessage());
                iae.initCause(ex);
                throw iae;
            }
        }
    }

    /**
     * Format the Date as String, using the specified TimeUnit.
     */
    public String getAsText() {
        Duration value = (Duration) getValue();
        return (value != null ? Long.toString(value.getQuantity()) : "");
    }

}
