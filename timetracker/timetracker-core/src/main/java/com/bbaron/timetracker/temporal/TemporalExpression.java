package com.bbaron.timetracker.temporal;

import java.util.Date;

public interface TemporalExpression {

    boolean includes(Date date);

}
