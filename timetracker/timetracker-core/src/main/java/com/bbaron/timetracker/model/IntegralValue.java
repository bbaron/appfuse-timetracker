package com.bbaron.timetracker.model;


public abstract class IntegralValue<T> {

    private final T value;
    
    public IntegralValue(T value, int lowerBound, int upperBound) {
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

    public T getValue() {
        return value;
    }

    public final Integer toInteger() {
        return value == null ? null : Integer.valueOf(nullSafeToInteger(value));
    }

    protected abstract int nullSafeToInteger(T value);
    
    @Override
    public String toString() {
        return value == null ? "null" : toInteger().toString();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((value == null) ? 0 : value.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
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
