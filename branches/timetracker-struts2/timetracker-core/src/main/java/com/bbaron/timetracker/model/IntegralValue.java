package com.bbaron.timetracker.model;


public abstract class IntegralValue<T> {

    private final T value;
    
    public IntegralValue(final T value, final int lowerBound, final int upperBound) {
        if (value != null) {
            int n = nullSafeToInteger(value);
            if (n < lowerBound || n > upperBound) {
                throw new IllegalArgumentException("value " + n + " is not between " + lowerBound + " and " + upperBound);
            }
        }
        this.value = value;
    }
    
    public IntegralValue(T value) {
        this(value, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    public final T getValue() {
        return value;
    }

    public final Integer toInteger() {
        return value == null ? null : Integer.valueOf(nullSafeToInteger(value));
    }

    protected abstract int nullSafeToInteger(T value);
    
    @Override
    public final String toString() {
        return value == null ? "null" : toInteger().toString();
    }

    @Override
    public final int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((value == null) ? 0 : value.hashCode());
        return result;
    }

    @Override
    public final boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        @SuppressWarnings("unchecked")
        IntegralValue other = (IntegralValue) obj;
        if (value == null) {
            if (other.value != null)
                return false;
        } else if (!value.equals(other.value))
            return false;
        return true;
    }
    
}
