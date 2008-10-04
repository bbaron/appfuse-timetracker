package com.bbaron.timetracker.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.bbaron.timetracker.model.Entity;


/**
 * Generic DAO (Data Access Object) with common methods to CRUD POJOs.
 *
 * <p>Extend this interface if you want type safe (no casting necessary) DAO's for your
 * domain entities.
 *
 * @param <T> a type variable
 * @param <PK> the primary key for that type
 */
public interface GenericDao <PK extends Serializable, T extends Entity<PK>> {

    /**
     * Generic method used to get all entities of a particular type. This
     * is the same as lookup up all rows in a table.
     * @return List of populated entities
     */
    List<T> getAll();

    /**
     * Generic method to get an entity based on class and identifier. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if
     * nothing is found.
     *
     * @param id the identifier (primary key) of the entity to get
     * @return a populated entity
     * @see org.springframework.orm.ObjectRetrievalFailureException
     */
    T get(PK id);

    /**
     * Checks for existence of an entity of type T using the id arg.
     * @param id the id of the entity
     * @return - true if it exists, false if it doesn't
     */
    boolean exists(PK id);

    /**
     * Generic method to save an entity - handles both update and insert.
     * @param entity the entity to save
     * @return the persisted entity
     */
    T save(T entity);

    /**
     * Generic method to delete an entity based on class and id
     * @param id the identifier (primary key) of the entity to remove
     */
    void remove(PK id);
    
    /**
     * Gets all records without duplicates.
     * <p>Note that if you use this method, it is imperative that your model
     * classes correctly implement the hashcode/equals methods</p>
     * @return List of populated entities
     */
    List<T> getAllDistinct();
    

    /**
     * Find a list of records by using a named query
     * @param queryName query name of the named query
     * @param queryParams a map of the query names and the values
     * @return a list of the records found
     */
    List<T> findByNamedQuery(String queryName, Map<String, Object> queryParams);
}