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
        Timestamp timestamp = rs.getTimestamp(names[0]);
        if (rs.wasNull()) {
            return null;
        }
        return new LocalDate(timestamp.getTime());
    }

    @Override
    protected void doSet(PreparedStatement st, Object value, int index) throws HibernateException, SQLException {
        LocalDate localDate = (LocalDate) value;
        st.setTimestamp(index, new Timestamp(localDate.toDateTimeAtStartOfDay().getMillis()));
    }
    

}
