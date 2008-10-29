package com.domainlanguage.util.propertyeditors;

import java.beans.PropertyEditorSupport;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

import com.domainlanguage.time.CalendarDate;
import com.domainlanguage.util.StringUtils;

/**
 * Property editor for {@link CalendarDate}, supporting a custom
 * <code>java.text.DateFormat</code>.
 * 
 * <p>
 * This is not meant to be used as system PropertyEditor but rather as
 * locale-specific date editor within custom controller code, parsing
 * user-entered number strings into Date properties of beans and rendering them
 * in the UI form.
 * 
 * <p>
 * In web MVC code, this editor will typically be registered with
 * <code>binder.registerCustomEditor</code> calls in a custom
 * <code>initBinder</code> method.
 * 
 */
public class CustomCalendarDateEditor extends PropertyEditorSupport {
    private final DateFormat dateFormat;

    private final boolean allowEmpty;

    private final int exactDateLength;

    /**
     * Create a new CustomCalendarDateEditor instance, using the given
     * DateFormat for parsing and rendering.
     * <p>
     * The "allowEmpty" parameter states if an empty String should be allowed
     * for parsing, i.e. get interpreted as null value. Otherwise, an
     * IllegalArgumentException gets thrown in that case.
     * <p>
     * The "exactDateLength" parameter states that IllegalArgumentException gets
     * thrown if the String does not exactly match the length specified. This is
     * useful because SimpleDateFormat does not enforce strict parsing of the
     * year part, not even with <code>setLenient(false)</code>. Without an
     * "exactDateLength" specified, the "01/01/05" would get parsed to
     * "01/01/0005".
     * 
     * @param dateFormat
     *            DateFormat to use for parsing and rendering
     * @param allowEmpty
     *            if empty strings should be allowed
     * @param exactDateLength
     *            the exact expected length of the date String
     */
    public CustomCalendarDateEditor(DateFormat dateFormat, boolean allowEmpty, int exactDateLength) {
        super();
        this.dateFormat = dateFormat;
        this.allowEmpty = allowEmpty;
        this.exactDateLength = exactDateLength;
    }

    /**
     * Create a new CustomCalendarDateEditor instance, using the given
     * DateFormat for parsing and rendering.
     * <p>
     * The "allowEmpty" parameter states if an empty String should be allowed
     * for parsing, i.e. get interpreted as null value. Otherwise, an
     * IllegalArgumentException gets thrown in that case.
     * 
     * @param dateFormat
     *            DateFormat to use for parsing and rendering
     * @param allowEmpty
     *            if empty strings should be allowed
     */
    public CustomCalendarDateEditor(DateFormat dateFormat, boolean allowEmpty) {
        super();
        this.dateFormat = dateFormat;
        this.allowEmpty = allowEmpty;
        this.exactDateLength = -1;
    }

    /**
     * Parse the Date from the given text, using the specified DateFormat.
     */
    public void setAsText(String text) throws IllegalArgumentException {
        if (this.allowEmpty && !StringUtils.hasText(text)) {
            // Treat empty String as null value.
            setValue(null);
        } else if (text != null && this.exactDateLength >= 0 && text.length() != this.exactDateLength) {
            throw new IllegalArgumentException("Could not parse date: it is not exactly" + this.exactDateLength
                    + "characters long");
        } else {
            try {
                Date date = this.dateFormat.parse(text);
                Calendar cal = Calendar.getInstance();
                cal.setTime(date);
                CalendarDate calDate = CalendarDate.date(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal
                        .get(Calendar.DAY_OF_MONTH));
                setValue(calDate);
            } catch (ParseException ex) {
                IllegalArgumentException iae = new IllegalArgumentException("Could not parse date: " + ex.getMessage());
                iae.initCause(ex);
                throw iae;
            }
        }
    }

    /**
     * Format the Date as String, using the specified DateFormat.
     */
    public String getAsText() {
        CalendarDate value = (CalendarDate) getValue();
        Calendar cal = Calendar.getInstance();
        cal.set(value.getYear(), value.getMonth(), value.getDay(), 0, 0, 0);
        return (value != null ? this.dateFormat.format(value) : "");
    }

}
