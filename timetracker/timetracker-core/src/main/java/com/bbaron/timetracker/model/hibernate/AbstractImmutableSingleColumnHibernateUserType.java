package com.bbaron.timetracker.model.hibernate;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.hibernate.HibernateException;
import org.hibernate.type.NullableType;
import org.hibernate.usertype.UserType;
import org.springframework.util.Assert;

public abstract class AbstractImmutableSingleColumnHibernateUserType implements UserType {

    private final Class<?> returnedClass;
    private final NullableType nullableType;
    private final int[] sqlTypes;

    public AbstractImmutableSingleColumnHibernateUserType(Class<?> returnedClass, NullableType nullableType) {
        Assert.notNull(returnedClass);
        Assert.notNull(nullableType);
        this.returnedClass = returnedClass;
        this.nullableType = nullableType;
        this.sqlTypes = new int[] { nullableType.sqlType() };
    }

    public NullableType getNullableType() {
        return nullableType;
    }

    @Override
    public final Object assemble(Serializable cached, Object unused) throws HibernateException {
        return cached;
    }

    @Override
    public final Object deepCopy(Object value) throws HibernateException {
        return value;
    }

    @Override
    public final Serializable disassemble(Object value) throws HibernateException {
        return (Serializable) value;
    }

    @Override
    public final boolean equals(Object x, Object y) throws HibernateException {
        if (x == y) {
            return true;
        }
        if (x == null || y == null) {
            return false;
        }
        return x.equals(y);
    }

    @Override
    public final int hashCode(Object x) throws HibernateException {
        return x.hashCode();
    }

    @Override
    public final Object replace(Object original, Object unused1, Object unused2) throws HibernateException {
        return original;
    }

    @SuppressWarnings("unchecked")
    @Override
    public final Class returnedClass() {
        return this.returnedClass;
    }

    @Override
    public final int[] sqlTypes() {
        return this.sqlTypes;
    }

    @Override
    public final void nullSafeSet(PreparedStatement st, Object value, int index) throws HibernateException,
            SQLException {
        if (value == null) {
            st.setNull(index, sqlTypes[0]);
        } else {
            doSet(st, value, index);
        }
    }

    @Override
    public final boolean isMutable() {
        return false;
    }

    protected abstract void doSet(PreparedStatement st, Object value, int index) throws HibernateException,
            SQLException;

}
