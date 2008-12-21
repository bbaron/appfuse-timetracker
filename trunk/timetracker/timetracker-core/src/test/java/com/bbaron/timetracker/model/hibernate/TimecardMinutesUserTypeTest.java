package com.bbaron.timetracker.model.hibernate;

import org.hibernate.type.NullableType;

import com.bbaron.timetracker.temporal.TimecardMinutes;

public class TimecardMinutesUserTypeTest extends AbstractIntegralValueUserTypeTest<TimecardMinutes> {

    private static final TimecardMinutes VALUE = TimecardMinutes.minutes(13);

    @Override
    protected AbstractImmutableSingleColumnHibernateUserType createUserType(Class<?> returnedClass2,
            NullableType nullType) {
        return new TimecardMinutesUserType();
    }

    @Override
    protected Integer getExpectedPersistedValue() {
        return VALUE.getValue().getMinutes();
    }

    @Override
    protected TimecardMinutes getExpectedReturnedValue() {
        return VALUE;
    }

    @Override
    protected Class<TimecardMinutes> getReturnedClass() {
        return TimecardMinutes.class;
    }


}
