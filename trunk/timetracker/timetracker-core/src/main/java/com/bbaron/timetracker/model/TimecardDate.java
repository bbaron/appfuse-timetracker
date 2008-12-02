package com.bbaron.timetracker.model;

import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import com.bbaron.timetracker.util.Constants;

public class TimecardDate {

    private final LocalDate localDate;

    private TimecardDate(int year, int month, int day) {
        localDate = new LocalDate(year, month, day);
    }

    private TimecardDate(LocalDate localDate) {
        this.localDate = localDate; 
    }

    public String toString(String dateFormat) {
        return localDate.toString(dateFormat);
    }
    
    @Override
    public String toString() {
        return toString(Constants.SYSTEM_DATE_FORMAT);
    }
    
    public long getMillis() {
        return localDate.toDateTimeAtStartOfDay().getMillis();
    }

    public TimecardDate plusDays(int days) {
        return new TimecardDate(localDate.plusDays(days));
    }

    public static TimecardDate date(int year, int month, int day) {
        return new TimecardDate(year, month, day);
    }
    
    public static TimecardDate date(String date, String format) {
        DateTimeFormatter formatter = DateTimeFormat.forPattern(format);
        LocalDate ld = formatter.parseDateTime(date).toLocalDate();
        return new TimecardDate(ld);
    }

    public static TimecardDate today() {
        return new TimecardDate(new LocalDate());
    }

    public static TimecardDate date(long time) {
        return new TimecardDate(new LocalDate(time));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((localDate == null) ? 0 : localDate.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof TimecardDate))
            return false;
        TimecardDate other = (TimecardDate) obj;
        if (localDate == null) {
            if (other.localDate != null)
                return false;
        } else if (!localDate.equals(other.localDate))
            return false;
        return true;
    }


}
