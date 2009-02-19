package com.bbaron.timetracker.dao.hibernate;

import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.persister.entity.EntityPersister;

import com.bbaron.timetracker.dao.BaseDaoTestCase;


public class HibernateConfigurationTest extends BaseDaoTestCase {
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @SuppressWarnings("unchecked")
    public void testColumnMapping() throws Exception {
        super.setComplete();
        Session session = sessionFactory.openSession();
        try {
            Map metadata = sessionFactory.getAllClassMetadata();
            logger.debug("metadata size = " + metadata.size());
            String classname = "com.bbaron.timetracker.model.User";
            logger.debug("Trying select * from: " + classname);
            Query qu = session.createQuery("from " + classname + " c");
            qu.iterate();
            logger.debug("ok: " + classname);
            for (Object o : metadata.values()) {
                EntityPersister persister = (EntityPersister) o;
                String className = persister.getEntityName();
                logger.debug("Trying select * from: " + className);
                Query q = session.createQuery("from " + className + " c");
                q.iterate();
                logger.debug("ok: " + className);
            }
        } finally {
            session.close();
        }
    }
}
