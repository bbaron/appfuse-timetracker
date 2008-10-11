package com.bbaron.timetracker.dao.hibernate;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;

import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.bbaron.timetracker.dao.GenericDao;
import com.bbaron.timetracker.model.IEntity;

public class GenericDaoHibernate<PK extends Serializable, T extends IEntity<PK>> extends HibernateDaoSupport implements
        GenericDao<PK, T> {

    private Class<T> entityClass;
    
    public GenericDaoHibernate(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    @Override
    public boolean exists(PK id) {
        return loadOrGet(id, false) != null;
    }

    @Override
    public List<T> findByNamedQuery(String queryName, Map<String, Object> queryParams) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public T get(PK id) {
        return (T) loadOrGet(id, true);
    }
    
    @SuppressWarnings("unchecked")
    protected T loadOrGet(PK id, boolean shouldExist) {
        HibernateTemplate t = getHibernateTemplate();
        return (T) (shouldExist ? t.load(entityClass, id) : t.get(entityClass, id));        
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<T> getAll() {
        return getHibernateTemplate().loadAll(entityClass);
    }

    @Override
    public List<T> getAllDistinct() {
        Collection<T> result = new LinkedHashSet<T>(getAll());
        return new ArrayList<T>(result);
    }

    @Override
    public void remove(PK id) {
        // TODO Auto-generated method stub

    }

    @Override
    public T save(T entity) {
        // TODO Auto-generated method stub
        return null;
    }

}
