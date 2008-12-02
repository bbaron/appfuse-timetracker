package com.bbaron.timetracker.model.hibernate;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Date;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;

import com.bbaron.timetracker.model.TimecardDate;

public class TimecardDateUserType extends AbstractImmutableSingleColumnHibernateUserType {

    public TimecardDateUserType() {
        super(TimecardDate.class, Hibernate.DATE);
    }

    @Override
    public Object nullSafeGet(ResultSet rs, String[] names, Object owner) throws HibernateException, SQLException {
        Timestamp value = rs.getTimestamp(names[0]);
        if (rs.wasNull()) {
            return null;
        }
        return TimecardDate.date(value.getTime());
    }

    @Override
    protected void doSet(PreparedStatement st, Object object, int index) throws HibernateException, SQLException {
        TimecardDate value = (TimecardDate) object;
        st.setDate(index, new Date(value.getMillis()));
    }

}
