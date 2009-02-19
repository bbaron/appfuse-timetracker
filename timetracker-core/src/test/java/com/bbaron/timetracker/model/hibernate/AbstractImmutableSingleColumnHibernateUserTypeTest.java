package com.bbaron.timetracker.model.hibernate;

import static org.junit.Assert.*;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.hibernate.type.NullableType;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(JMock.class)
public abstract class AbstractImmutableSingleColumnHibernateUserTypeTest<R, P> {

    private static final int COLUMN_INDEX = 0;

    private Mockery context = new JUnit4Mockery();

    private static final Serializable SERIALIZABLE = new Integer(1);
    private static final Object OBJECT = new Object();
    private static final String COLUMN = "col";
    private static final String[] COLUMN_NAMES = new String[] { COLUMN };

    private AbstractImmutableSingleColumnHibernateUserType type;
    private Class<R> returnedClass;
    private NullableType nullableType;

    private ResultSet mockResultSet;
    private PreparedStatement mockPreparedStatement;

    @Before
    public void setUp() throws Exception {
        this.returnedClass = getReturnedClass();
        this.nullableType = getNullType();
        type = createUserType(getReturnedClass(), getNullType());
        mockResultSet = context.mock(ResultSet.class);
        mockPreparedStatement = context.mock(PreparedStatement.class);
    }

    @Test
    public void test_assemble() throws Exception {
        assertSame(SERIALIZABLE, type.assemble(SERIALIZABLE, null));
    }

    @Test
    public void test_isMutable() throws Exception {
        assertFalse(type.isMutable());
    }

    @Test
    public void test_returnedClass() throws Exception {
        assertSame(this.returnedClass, type.returnedClass());
    }

    @Test
    public void test_sqlTypes() throws Exception {
        int[] sqlTypes = type.sqlTypes();
        assertNotNull(sqlTypes);
        assertEquals(1, sqlTypes.length);
        assertEquals(nullableType.sqlType(), sqlTypes[0]);
    }

    @Test
    public void test_getNullableType() throws Exception {
        assertSame(this.nullableType, type.getNullableType());
    }

    @Test
    public void test_replace() throws Exception {
        assertSame(OBJECT, type.replace(OBJECT, null, null));
    }

    @Test
    public void test_deepCopy() throws Exception {
        assertSame(OBJECT, type.deepCopy(OBJECT));
    }

    @Test
    public void test_disassemble() throws Exception {
        assertSame(SERIALIZABLE, type.disassemble(SERIALIZABLE));
    }

    @Test
    public void test_hashCode() throws Exception {
        assertEquals(OBJECT.hashCode(), type.hashCode(OBJECT));
    }

    @Test
    public void test_equals() throws Exception {
        assertTrue(type.equals(OBJECT, OBJECT));
        assertTrue(type.equals(SERIALIZABLE, new Integer(1)));
        assertTrue(type.equals(null, null));
        assertFalse(type.equals(null, OBJECT));
        assertFalse(type.equals(OBJECT, null));
        assertFalse(type.equals(OBJECT, SERIALIZABLE));
    }

    private class NullSafeGetExpectations extends Expectations {
        public NullSafeGetExpectations(boolean wasNull) throws Exception {
            oneOf(mockResultSet).wasNull();
            will(Expectations.returnValue(wasNull));
        }
    }

    @Test
    public void test_nullSafeGet_Object() throws Exception {
        Object expected = getExpectedReturnedValue();
        Expectations expectations = new NullSafeGetExpectations(false);
        setUpNullSafeGetExpectations(expectations, mockResultSet, COLUMN, getExpectedPersistedValue());
        context.checking(expectations);
        Object actual = type.nullSafeGet(mockResultSet, COLUMN_NAMES, null);
        assertEquals(expected, actual);
    }

    @Test
    public void test_nullSafeGet_null() throws Exception {
        Expectations expectations = new NullSafeGetExpectations(true);
        setUpNullSafeGetExpectations(expectations, mockResultSet, COLUMN, null);
        context.checking(expectations);
        Object actual = type.nullSafeGet(mockResultSet, COLUMN_NAMES, null);
        assertNull(actual);
    }

    @Test
    public void test_nullSafeSet_null() throws Exception {
        Expectations expectations = new Expectations() {
            {
                oneOf(mockPreparedStatement).setNull(COLUMN_INDEX, type.getNullableType().sqlType());
            }
        };
        context.checking(expectations);
        type.nullSafeSet(mockPreparedStatement, null, COLUMN_INDEX);
    }

    @Test
    public void test_nullSafeSet_Object() throws Exception {
        Expectations expectations = new Expectations();
        setUpNullSafeSetExpectations(expectations, mockPreparedStatement, COLUMN_INDEX, getExpectedPersistedValue());
        context.checking(expectations);
        type.nullSafeSet(mockPreparedStatement, getExpectedReturnedValue(), COLUMN_INDEX);
    }

    protected abstract void setUpNullSafeSetExpectations(Expectations expectations, PreparedStatement st, int index,
            P persistedValue) throws Exception;
    
    protected abstract void setUpNullSafeGetExpectations(Expectations expectations, ResultSet resultSet, String column,
            P persistedValue) throws Exception;

    protected abstract R getExpectedReturnedValue();

    protected abstract P getExpectedPersistedValue();

    protected abstract AbstractImmutableSingleColumnHibernateUserType createUserType(Class<?> returnedClass2,
            NullableType nullType);

    protected abstract Class<R> getReturnedClass();

    protected abstract NullableType getNullType();
}
