package com.bbaron.timetracker.temporal;

import org.joda.time.Duration;
import org.joda.time.Minutes;
import org.springframework.util.Assert;

import com.bbaron.timetracker.model.TemporalValue;

public final class TimecardMinutes extends TemporalValue<Minutes> {

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

    @Override
    public Duration toDuration() {
        return Duration.standardMinutes(super.toInteger());
    }    
    
}
