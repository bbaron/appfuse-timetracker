package com.bbaron.timetracker.model;

import org.joda.time.Duration;

public abstract class TemporalValue<T> extends IntegralValue<T> {

    public TemporalValue(T value) {
        super(value);
    }

    public TemporalValue(T value, int lowerBound, int upperBound) {
        super(value, lowerBound, upperBound);
    }

    public abstract Duration toDuration();
}
