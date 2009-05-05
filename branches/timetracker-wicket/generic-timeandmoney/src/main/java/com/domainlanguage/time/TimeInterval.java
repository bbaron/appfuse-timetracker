/**
 * Copyright (c) 2004 Domain Language, Inc. (http://domainlanguage.com) This
 * free software is distributed under the "MIT" licence. See file licence.txt.
 * For more information, see http://timeandmoney.sourceforge.net.
 */

package com.domainlanguage.time;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.domainlanguage.intervals.Interval;
import com.domainlanguage.util.ImmutableIterator;


public class TimeInterval implements Comparable<TimeInterval>, Serializable {

    private final Interval<TimePoint> interval;
    private final boolean startClosed;
    private final boolean endClosed;
    
    private TimeInterval(Interval<TimePoint> interval, boolean startClosed, boolean endClosed) {
        if (interval == null) {
            throw new IllegalArgumentException("interval is required");
        }
        this.interval = interval.newOfSameType(interval.lowerLimit(), startClosed,
                interval.upperLimit(), endClosed);
        this.startClosed = startClosed;
        this.endClosed = endClosed;
    }
  
    public int compareTo(TimeInterval other) {
        return interval.compareTo(other.interval);
    }

    
    public List<TimeInterval> complementRelativeTo(TimeInterval other) {        
        List<TimeInterval> result = new ArrayList<TimeInterval>();
        List<Interval<TimePoint>> intervals = interval.complementRelativeTo(other.interval);
        for (Interval<TimePoint> interval : intervals) {
            result.add(new TimeInterval(interval, startClosed, endClosed));
        }
        return result;
    }

    
    public boolean covers(TimeInterval other) {        
        return interval.covers(other.interval);
    }

    
    public TimeInterval emptyOfSameType() {        
        return new TimeInterval(interval.emptyOfSameType(), startClosed, endClosed);
    }
    
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other instanceof TimeInterval == false) {
            return false;
        }
        TimeInterval rhs = (TimeInterval) other;
        return interval.equals(rhs.interval);
    }

    
    public TimeInterval gap(TimeInterval other) {
        return new TimeInterval(interval.gap(other.interval), startClosed, endClosed);
    }

    
    public int hashCode() {        
        return interval.hashCode();
    }

    
    public boolean hasLowerLimit() {        
        return interval.hasLowerLimit();
    }

    
    public boolean hasUpperLimit() {        
        return interval.hasUpperLimit();
    }

    
    public boolean includesLowerLimit() {
        return interval.includesLowerLimit();
    }

    
    public boolean includesUpperLimit() {        
        return interval.includesUpperLimit();
    }

    
    public TimeInterval intersect(TimeInterval other) {
        Interval<TimePoint> t = interval.intersect(other.interval);
        return new TimeInterval(t, startClosed, endClosed);
    }

    
    public boolean intersects(TimeInterval other) {        
        return interval.intersects(other.interval);
    }

    
    public boolean isAbove(TimePoint value) {        
        return interval.isAbove(value);
    }

    
    public boolean isBelow(TimePoint value) {
        return interval.isBelow(value);
    }

    
    public boolean isClosed() {
        return interval.isClosed();
    }

    
    public boolean isEmpty() {
        return interval.isEmpty();
    }

    
    public boolean isOpen() {
        return interval.isOpen();
    }

    
    public boolean isSingleElement() {
        return interval.isSingleElement();
    }

    
    public TimePoint lowerLimit() {
        return interval.lowerLimit();
    }

    
    public TimeInterval newOfSameType(TimePoint lower, boolean isLowerClosed, TimePoint upper,
            boolean isUpperClosed) {        
        return new TimeInterval(interval.newOfSameType(lower, isLowerClosed, upper, isUpperClosed), isLowerClosed, isUpperClosed);
    }

    
    public String toString() {
        return interval.toString();
    }

    
    public TimePoint upperLimit() {
        return interval.upperLimit();
    }

	public static TimeInterval over(TimePoint start, boolean closedStart, TimePoint end, boolean closedEnd) {
		return new TimeInterval(start, closedStart, end, closedEnd);
	}

	public static TimeInterval over(TimePoint start, TimePoint end) {
		//Uses the common default for time intervals, [start, end).
		return over(start, true, end, false);
	}

	public static TimeInterval startingFrom(TimePoint start, boolean startClosed, Duration length, boolean endClosed) {
		TimePoint end = start.plus(length);
		return over(start, startClosed, end, endClosed);
	}
	
	public static TimeInterval startingFrom(TimePoint start, Duration length) {
		//Uses the common default for time intervals, [start, end).
		return startingFrom(start, true, length, false);
	}

	public static TimeInterval preceding(TimePoint end, boolean startClosed, Duration length, boolean endClosed) {
		TimePoint start = end.minus(length);
		return over(start, startClosed, end, endClosed);
	}
	
	public static TimeInterval preceding(TimePoint end, Duration length) {
		//Uses the common default for time intervals, [start, end).
		return preceding(end, true, length, false);
	}

	public static TimeInterval closed(TimePoint start, TimePoint end) {
		return over(start, true, end, true);
	}

	public static TimeInterval open(TimePoint start, TimePoint end) {
		return over(start, false, end, false);
	}
	
	public static TimeInterval everFrom(TimePoint start) {
		return over(start, null);
	} 

	public static TimeInterval everPreceding(TimePoint end) {
		return over(null, end);
	} 

	public TimeInterval(TimePoint start, boolean startIncluded, TimePoint end, boolean endIncluded) {
	    interval = Interval.newInstance(start, startIncluded, end, endIncluded);
	    this.startClosed = startIncluded;
	    this.endClosed = endIncluded;
	}
	
	public boolean isBefore(TimePoint point) {
		return interval.isBelow(point);
	}

	public boolean isAfter(TimePoint point) {
		return interval.isAbove(point);
	}

	public Duration length() {
		long difference = end().millisecondsFromEpoc - start().millisecondsFromEpoc;
		return Duration.milliseconds(difference);
	}
	
	public Iterator<TimePoint> daysIterator() {
		return new ImmutableIterator<TimePoint>() {
			TimePoint next = start();
			public boolean hasNext() {
				return end().isAfter(next);
			}	
			public TimePoint next() {
				TimePoint current = next;
				next = next.nextDay();
				return current;
			}
		};
	}

	@SuppressWarnings("unchecked")
    public Iterator<TimePoint> subintervalIterator(Duration subintervalLength) {
        final Duration segmentLength = subintervalLength;
        final TimeInterval totalInterval = new TimeInterval(interval, startClosed, endClosed);
        return new ImmutableIterator() {
            TimeInterval next = segmentLength.startingFrom(start());
            public boolean hasNext() {
                return totalInterval.covers(next);
            }   
            public Object next() {
                if (!hasNext()) return null;
                Object current = next;
                next = segmentLength.startingFrom(next.end());
                return current;
            }
        };
	}

	public TimePoint start() {
		return interval.lowerLimit();
	}
	
	public TimePoint end() {
		return interval.upperLimit();
	}
	
    public boolean includes(TimePoint value) {
        return interval.includes(value);
    }
    
    //Only for use by persistence mapping frameworks
    //<rant>These methods break encapsulation and we put them in here begrudgingly</rant>
    TimeInterval() {
        interval = null;
        startClosed = false;
        endClosed = false;
    }

}
 