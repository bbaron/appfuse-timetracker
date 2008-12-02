package com.bbaron.timetracker.model;

import org.joda.time.Minutes;
import org.springframework.util.Assert;

public final class TimecardMinutes extends IntegralValue<Minutes> {

    public static final TimecardMinutes[] minutes = new TimecardMinutes[61];
    public static final TimecardMinutes ZERO = minutes[0] = new TimecardMinutes(0);
    
    private TimecardMinutes(int minutes) {
        super(Minutes.minutes(minutes), 0, 60);
    }
    
    @Override
    protected final int nullSafeToInteger(final Minutes value) {
        return value.getMinutes();
    }

    public static TimecardMinutes minutes(final int m) {
        Assert.isTrue(0 <= m && m <= 60, "minutes must be [0, 60]");
        if (minutes[m] == null) {
            minutes[m] = new TimecardMinutes(m);
        }
        return minutes[m];
    }    
    
}
