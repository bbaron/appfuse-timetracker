package com.bbaron.timetracker.temporal;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

public class ScheduleImpl implements Schedule {

    private Collection<ScheduleElement> scheduleElements = new ArrayList<ScheduleElement>();
    
    @Override
    public List<Date> dates(String eventArg, DateRange during) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean isOccurring(String eventArg, Date date) {
        for (ScheduleElement elem : scheduleElements) {
            if (elem.isOccurring(eventArg, date)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Date nextOccurence(String eventArg, Date date) {
        // TODO Auto-generated method stub
        return null;
    }

    public void setScheduleElements(Collection<ScheduleElement> scheduleElements) {
        this.scheduleElements = scheduleElements;
    }

}
