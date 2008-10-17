package com.bbaron.timetracker.dao;

import java.io.Serializable;

import junit.framework.Assert;

import org.hibernate.SessionFactory;
import org.springframework.dao.DataAccessException;

import com.bbaron.timetracker.dao.hibernate.GenericDaoHibernate;

/**
 * Abstract base class for easy testing of dao's. To extend, simply implement
 * the abstract methods.
 * 
 * @author bbaron
 * 
 * @param <T>
 * @param <PK>
 */
public abstract class AbstractGenericDaoTestCase<T, PK extends Serializable> extends BaseDaoTestCase {

    private GenericDaoHibernate<T, PK> dao;
    private Class<T> entityClass;
    private SessionFactory sessionFactory;
    private PK validId;
    private PK invalidId;

    /**
     * Constructor to initialize with test values.
     * 
     * @param entityClass
     *            the entity class to test.
     * @param validId
     *            an identifier of a persistent entity
     * @param invalidId
     *            an identifier of a non-persistent entity.
     */
    protected AbstractGenericDaoTestCase(Class<T> entityClass, PK validId, PK invalidId) {
        super();
        this.entityClass = entityClass;
        this.validId = validId;
        this.invalidId = invalidId;
    }

    /**
     * Clears the id from the entity. Set the id field to <code>null</code> for
     * testing purposes.
     * 
     * @param entity
     *            to clear id.
     */
    protected abstract void clearIdFrom(T entity);

    /**
     * Creates an entity instance populated so that it can be persisted.
     * 
     * @return the created entity.
     */
    protected abstract T createEntity();

    /**
     * Gets the actual value from the entity that should equal the value
     * returned by {@linkplain #getExpected()}
     * 
     * @param entity
     *            contains the actual value.
     * @return actual value.
     */
    protected abstract Object getActualFrom(T entity);

    /**
     * Get a value expected to exist in a property of an entity. This value is
     * expected to equal the value returned by {@link #getActualFrom(Object)}.
     * That is, {@link Assert#assertEquals(expected, actual)} returns
     * <code>true</code>, where <code>expected = getExpected()</code> and
     * <code>actual = getActualFrom()</code>
     * 
     * @return the expected value.
     */
    protected abstract Object getExpected();

    /**
     * get primary key id from entity.
     * 
     * @param entity
     * @return key
     */
    protected abstract PK getIdFrom(T entity);

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    @Override
    protected void onSetUp() throws Exception {
        dao = new GenericDaoHibernate<T, PK>(entityClass);
        dao.setSessionFactory(sessionFactory);
        super.onSetUp();
    }

    @Override
    protected void onSetUpInTransaction() throws Exception {
        for (String sql : getSetUpStatements()) {
            super.getJdbcTemplate().execute(sql);
        }
    }
    
    protected abstract String[] getSetUpStatements();
    
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void testAddAndRemove() throws Exception {
        T entity = createEntity();

        entity = dao.save(entity);
        flush();

        assertNotNull(getIdFrom(entity));
        entity = dao.get(getIdFrom(entity));
        assertEquals(getExpected(), getActualFrom(entity));

        dao.remove(getIdFrom(entity));
        flush();

        try {
            dao.get(getIdFrom(entity));
            fail("getUser didn't throw DataAccessException");
        } catch (DataAccessException d) {
            assertNotNull(d);
        }
    }

    public void testExists() throws Exception {
        boolean b = dao.exists(validId);
        assertTrue(b);
    }

    public void testGet() throws Exception {
        Object entity = dao.get(validId);

        assertNotNull(entity);
    }

    public void testGetInvalid() throws Exception {
        try {
            dao.get(invalidId);
            fail("unexpected entity found in database, failing test...");
        } catch (DataAccessException d) {
            assertTrue(d != null);
        }
    }

    public void testNotExists() throws Exception {
        boolean b = dao.exists(invalidId);
        assertFalse(b);
    }

    public void testUpdate() throws Exception {
        T entity = dao.get(validId);
        updateEntity(entity);
        dao.save(entity);
        flush();

    }

	protected abstract void updateEntity(T entity);
}
