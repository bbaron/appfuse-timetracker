package com.bbaron.timetracker.temporal;

import java.util.Date;

public interface Occurable {
    public boolean isOccurring(String eventArg, Date aDate);
}
