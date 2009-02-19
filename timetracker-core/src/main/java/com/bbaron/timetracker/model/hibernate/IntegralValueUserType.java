package com.bbaron.timetracker.model.hibernate;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;

import com.bbaron.timetracker.model.IntegralValue;

public abstract class IntegralValueUserType<T extends IntegralValue<?>> extends AbstractImmutableSingleColumnHibernateUserType {

    public IntegralValueUserType(Class<T> returnedClass) {
        super(returnedClass, Hibernate.INTEGER);
    }

    @Override
    protected void doSet(PreparedStatement st, Object object, int index) throws HibernateException, SQLException {
        IntegralValue<?> value = (IntegralValue<?>) object;
        st.setInt(index, value.toInteger());
    }

    @Override
    public Object nullSafeGet(ResultSet rs, String[] names, Object owner) throws HibernateException, SQLException {
        Integer value = rs.getInt(names[0]);
        if (rs.wasNull()) {
            return null;
        }
        T object = construct(value.intValue());
        return object;
    }

    protected abstract T construct(int value);
}
