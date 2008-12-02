package com.bbaron.timetracker.model.hibernate;

import org.hibernate.type.NullableType;

import com.bbaron.timetracker.model.TimecardHours;

public class TimecardHoursUserTypeTest extends AbstractIntegralValueUserTypeTest<TimecardHours> {

    private static final TimecardHours VALUE = TimecardHours.hours(13);

    @Override
    protected AbstractImmutableSingleColumnHibernateUserType createUserType(Class<?> returnedClass2,
            NullableType nullType) {
        return new TimecardHoursUserType();
    }

    @Override
    protected Integer getExpectedPersistedValue() {
        return VALUE.getValue().getHours();
    }

    @Override
    protected TimecardHours getExpectedReturnedValue() {
        return VALUE;
    }

    @Override
    protected Class<TimecardHours> getReturnedClass() {
        return TimecardHours.class;
    }


}
