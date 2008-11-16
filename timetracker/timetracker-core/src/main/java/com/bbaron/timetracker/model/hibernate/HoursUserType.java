package com.bbaron.timetracker.model.hibernate;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.joda.time.Hours;

public class HoursUserType extends AbstractImmutableSingleColumnHibernateUserType {

    public HoursUserType() {
        super(Hours.class, Hibernate.INTEGER);
    }

    @Override
    public Object nullSafeGet(ResultSet rs, String[] names, Object owner) throws HibernateException, SQLException {
        int value = rs.getInt(names[0]);
        if (rs.wasNull()) {
            return null;
        }
        return Hours.hours(value);
    }

    @Override
    protected void doSet(PreparedStatement st, Object object, int index) throws HibernateException, SQLException {
        Hours value = (Hours) object;
        st.setInt(index, value.getHours());
    }

}
