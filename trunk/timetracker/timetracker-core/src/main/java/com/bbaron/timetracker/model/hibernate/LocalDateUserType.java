package com.bbaron.timetracker.model.hibernate;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.joda.time.LocalDate;

public class LocalDateUserType extends AbstractImmutableSingleColumnHibernateUserType {

    public LocalDateUserType() {
        super(LocalDate.class, Hibernate.TIMESTAMP);
    }

    @Override
    public Object nullSafeGet(ResultSet rs, String[] names, Object owner) throws HibernateException, SQLException {
        Timestamp value = rs.getTimestamp(names[0]);
        if (rs.wasNull()) {
            return null;
        }
        return new LocalDate(value.getTime());
    }

    @Override
    protected void doSet(PreparedStatement st, Object object, int index) throws HibernateException, SQLException {
        LocalDate value = (LocalDate) object;
        st.setTimestamp(index, new Timestamp(value.toDateTimeAtStartOfDay().getMillis()));
    }
    

}
