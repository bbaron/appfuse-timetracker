package com.bbaron.timetracker.model.hibernate;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.joda.time.Minutes;

public class MinutesUserType extends AbstractImmutableSingleColumnHibernateUserType {

    public MinutesUserType() {
        super(Minutes.class, Hibernate.INTEGER);
    }

    @Override
    public Object nullSafeGet(ResultSet rs, String[] names, Object owner) throws HibernateException, SQLException {
        int minutes = rs.getInt(names[0]);
        if (rs.wasNull()) {
            return null;
        }
        return Minutes.minutes(minutes);
    }

    @Override
    protected void doSet(PreparedStatement st, Object value, int index) throws HibernateException, SQLException {
        Minutes minutes = (Minutes) value;
        st.setInt(index, minutes.getMinutes());
    }

}
