package com.bbaron.timetracker.model.hibernate;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;

import org.hibernate.Hibernate;
import org.hibernate.type.NullableType;
import org.jmock.Expectations;
import org.joda.time.LocalDate;

public class LocalDateUserTypeTest extends AbstractImmutableSingleColumnHibernateUserTypeTest<LocalDate, Timestamp> {

    private static final LocalDate NINE_ELEVEN = new LocalDate(2001, 9, 11);
    
    @Override
    protected AbstractImmutableSingleColumnHibernateUserType createUserType(Class<?> returnedClass2,
            NullableType nullType) {
        return new LocalDateUserType();
    }

    @Override
    protected NullableType getNullType() {
        return Hibernate.TIMESTAMP;
    }

    @Override
    protected Class<LocalDate> getReturnedClass() {
        return LocalDate.class;
    }

    @Override
    protected LocalDate getExpectedReturnedValue() {
        return NINE_ELEVEN;
    }

    @Override
    protected Timestamp getExpectedPersistedValue() {
        return new Timestamp(NINE_ELEVEN.toDateTimeAtStartOfDay().getMillis());
    }

    @Override
    protected void setUpNullSafeGetExpectations(Expectations e, ResultSet rs, String column,
            Timestamp value) throws Exception {
        e.oneOf(rs).getTimestamp(column); 
        e.will(Expectations.returnValue(value));        
    }

    @Override
    protected void setUpNullSafeSetExpectations(Expectations e, PreparedStatement st, int index,
            Timestamp persistedValue) throws Exception {
        e.oneOf(st).setTimestamp(index, persistedValue);
    }

}
