package com.bbaron.timetracker.model.hibernate;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.hibernate.Hibernate;
import org.hibernate.type.NullableType;
import org.jmock.Expectations;

import com.bbaron.timetracker.model.IntegralValue;

public abstract class AbstractIntegralValueUserTypeTest<R extends IntegralValue<?>> extends
        AbstractImmutableSingleColumnHibernateUserTypeTest<R, Integer> {

    @Override
    protected NullableType getNullType() {
        return Hibernate.INTEGER;
    }

    @Override
    protected void setUpNullSafeGetExpectations(Expectations e, ResultSet rs, String column, Integer value)
            throws Exception {
        e.oneOf(rs).getInt(column);
        e.will(Expectations.returnValue(value == null ? 0 : value.intValue()));
    }

    @Override
    protected void setUpNullSafeSetExpectations(Expectations e, PreparedStatement st, int index, Integer value)
            throws Exception {
        e.oneOf(st).setInt(index, value.intValue());
    }

}
