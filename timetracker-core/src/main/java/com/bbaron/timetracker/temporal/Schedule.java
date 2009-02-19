package com.bbaron.timetracker.temporal;

import java.util.Date;
import java.util.List;

public interface Schedule extends Occurable {
    public List<Date> dates (String eventArg, DateRange during);
    public Date nextOccurence (String eventArg, Date aDate);

}
