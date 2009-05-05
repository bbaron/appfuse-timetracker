package com.bbaron.timetracker.dao.mock;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.bbaron.timetracker.dao.GenericDao;
import com.bbaron.timetracker.model.IEntity;

public abstract class MockGenericDao<T, PK extends Serializable> implements GenericDao<T, PK> {

    private boolean exists = false;
    private List<T> entities = createEntities();
    private Exception exception;
    private PK id;

    public void setId(PK id) {
        this.id = id;
    }

    protected abstract List<T> createEntities();
    
    public MockGenericDao() {
    }

    public void setException(Exception exception) {
        this.exception = exception;
    }

    private void throwIfExceptionSet() {
        if (exception != null) {
            try {
                throw exception.getClass().getConstructor(String.class).newInstance(exception.getMessage());
            } catch (Exception e) {
                throw new AssertionError();
            }
        }
    }

    public void setExists(boolean exists) {
        this.exists = exists;
    }

    public void setEntities(T... entities) {
        this.entities = new ArrayList<T>(Arrays.asList(entities));
    }

    @Override
    public boolean exists(PK id) {
        throwIfExceptionSet();
        return exists;
    }

    @Override
    public List<T> findByNamedQuery(String queryName, Map<String, Object> queryParams) {
        throwIfExceptionSet();
        return null;
    }

    @Override
    public T get(PK id) {
        throwIfExceptionSet();
        return entities.get(0);
    }

    @Override
    public List<T> getAll() {
        throwIfExceptionSet();
        return entities;
    }

    @Override
    public List<T> getAllDistinct() {
        throwIfExceptionSet();
        return entities;
    }

    @Override
    public void remove(PK id) {
        throwIfExceptionSet();
    }

    @SuppressWarnings("unchecked")
    @Override
    public T save(T object) {
        throwIfExceptionSet();
        if (id != null) {
            ((IEntity)object).setId(id);
        }

        return object;
    }

}
