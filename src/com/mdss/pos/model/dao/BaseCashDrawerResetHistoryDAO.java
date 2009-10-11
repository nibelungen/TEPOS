package com.mdss.pos.model.dao;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import com.mdss.pos.model.dao.CashDrawerResetHistoryDAO;
import org.hibernate.criterion.Order;

/**
 * This is an automatically generated DAO class which should not be edited.
 */
public abstract class BaseCashDrawerResetHistoryDAO extends com.mdss.pos.model.dao._RootDAO {

	// query name references


	public static CashDrawerResetHistoryDAO instance;

	/**
	 * Return a singleton of the DAO
	 */
	public static CashDrawerResetHistoryDAO getInstance () {
		if (null == instance) instance = new CashDrawerResetHistoryDAO();
		return instance;
	}

	public Class getReferenceClass () {
		return com.mdss.pos.model.CashDrawerResetHistory.class;
	}

    public Order getDefaultOrder () {
		return null;
    }

	/**
	 * Cast the object as a com.mdss.pos.model.CashDrawerResetHistory
	 */
	public com.mdss.pos.model.CashDrawerResetHistory cast (Object object) {
		return (com.mdss.pos.model.CashDrawerResetHistory) object;
	}

	public com.mdss.pos.model.CashDrawerResetHistory get(java.lang.Integer key)
	{
		return (com.mdss.pos.model.CashDrawerResetHistory) get(getReferenceClass(), key);
	}

	public com.mdss.pos.model.CashDrawerResetHistory get(java.lang.Integer key, Session s)
	{
		return (com.mdss.pos.model.CashDrawerResetHistory) get(getReferenceClass(), key, s);
	}

	public com.mdss.pos.model.CashDrawerResetHistory load(java.lang.Integer key)
	{
		return (com.mdss.pos.model.CashDrawerResetHistory) load(getReferenceClass(), key);
	}

	public com.mdss.pos.model.CashDrawerResetHistory load(java.lang.Integer key, Session s)
	{
		return (com.mdss.pos.model.CashDrawerResetHistory) load(getReferenceClass(), key, s);
	}

	public com.mdss.pos.model.CashDrawerResetHistory loadInitialize(java.lang.Integer key, Session s) 
	{ 
		com.mdss.pos.model.CashDrawerResetHistory obj = load(key, s); 
		if (!Hibernate.isInitialized(obj)) {
			Hibernate.initialize(obj);
		} 
		return obj; 
	}

/* Generic methods */

	/**
	 * Return all objects related to the implementation of this DAO with no filter.
	 */
	public java.util.List<com.mdss.pos.model.CashDrawerResetHistory> findAll () {
		return super.findAll();
	}

	/**
	 * Return all objects related to the implementation of this DAO with no filter.
	 */
	public java.util.List<com.mdss.pos.model.CashDrawerResetHistory> findAll (Order defaultOrder) {
		return super.findAll(defaultOrder);
	}

	/**
	 * Return all objects related to the implementation of this DAO with no filter.
	 * Use the session given.
	 * @param s the Session
	 */
	public java.util.List<com.mdss.pos.model.CashDrawerResetHistory> findAll (Session s, Order defaultOrder) {
		return super.findAll(s, defaultOrder);
	}

	/**
	 * Persist the given transient instance, first assigning a generated identifier. (Or using the current value
	 * of the identifier property if the assigned generator is used.) 
	 * @param cashDrawerResetHistory a transient instance of a persistent class 
	 * @return the class identifier
	 */
	public java.lang.Integer save(com.mdss.pos.model.CashDrawerResetHistory cashDrawerResetHistory)
	{
		return (java.lang.Integer) super.save(cashDrawerResetHistory);
	}

	/**
	 * Persist the given transient instance, first assigning a generated identifier. (Or using the current value
	 * of the identifier property if the assigned generator is used.) 
	 * Use the Session given.
	 * @param cashDrawerResetHistory a transient instance of a persistent class
	 * @param s the Session
	 * @return the class identifier
	 */
	public java.lang.Integer save(com.mdss.pos.model.CashDrawerResetHistory cashDrawerResetHistory, Session s)
	{
		return (java.lang.Integer) save((Object) cashDrawerResetHistory, s);
	}

	/**
	 * Either save() or update() the given instance, depending upon the value of its identifier property. By default
	 * the instance is always saved. This behaviour may be adjusted by specifying an unsaved-value attribute of the
	 * identifier property mapping. 
	 * @param cashDrawerResetHistory a transient instance containing new or updated state 
	 */
	public void saveOrUpdate(com.mdss.pos.model.CashDrawerResetHistory cashDrawerResetHistory)
	{
		saveOrUpdate((Object) cashDrawerResetHistory);
	}

	/**
	 * Either save() or update() the given instance, depending upon the value of its identifier property. By default the
	 * instance is always saved. This behaviour may be adjusted by specifying an unsaved-value attribute of the identifier
	 * property mapping. 
	 * Use the Session given.
	 * @param cashDrawerResetHistory a transient instance containing new or updated state.
	 * @param s the Session.
	 */
	public void saveOrUpdate(com.mdss.pos.model.CashDrawerResetHistory cashDrawerResetHistory, Session s)
	{
		saveOrUpdate((Object) cashDrawerResetHistory, s);
	}

	/**
	 * Update the persistent state associated with the given identifier. An exception is thrown if there is a persistent
	 * instance with the same identifier in the current session.
	 * @param cashDrawerResetHistory a transient instance containing updated state
	 */
	public void update(com.mdss.pos.model.CashDrawerResetHistory cashDrawerResetHistory) 
	{
		update((Object) cashDrawerResetHistory);
	}

	/**
	 * Update the persistent state associated with the given identifier. An exception is thrown if there is a persistent
	 * instance with the same identifier in the current session.
	 * Use the Session given.
	 * @param cashDrawerResetHistory a transient instance containing updated state
	 * @param the Session
	 */
	public void update(com.mdss.pos.model.CashDrawerResetHistory cashDrawerResetHistory, Session s)
	{
		update((Object) cashDrawerResetHistory, s);
	}

	/**
	 * Remove a persistent instance from the datastore. The argument may be an instance associated with the receiving
	 * Session or a transient instance with an identifier associated with existing persistent state. 
	 * @param id the instance ID to be removed
	 */
	public void delete(java.lang.Integer id)
	{
		delete((Object) load(id));
	}

	/**
	 * Remove a persistent instance from the datastore. The argument may be an instance associated with the receiving
	 * Session or a transient instance with an identifier associated with existing persistent state. 
	 * Use the Session given.
	 * @param id the instance ID to be removed
	 * @param s the Session
	 */
	public void delete(java.lang.Integer id, Session s)
	{
		delete((Object) load(id, s), s);
	}

	/**
	 * Remove a persistent instance from the datastore. The argument may be an instance associated with the receiving
	 * Session or a transient instance with an identifier associated with existing persistent state. 
	 * @param cashDrawerResetHistory the instance to be removed
	 */
	public void delete(com.mdss.pos.model.CashDrawerResetHistory cashDrawerResetHistory)
	{
		delete((Object) cashDrawerResetHistory);
	}

	/**
	 * Remove a persistent instance from the datastore. The argument may be an instance associated with the receiving
	 * Session or a transient instance with an identifier associated with existing persistent state. 
	 * Use the Session given.
	 * @param cashDrawerResetHistory the instance to be removed
	 * @param s the Session
	 */
	public void delete(com.mdss.pos.model.CashDrawerResetHistory cashDrawerResetHistory, Session s)
	{
		delete((Object) cashDrawerResetHistory, s);
	}
	
	/**
	 * Re-read the state of the given instance from the underlying database. It is inadvisable to use this to implement
	 * long-running sessions that span many business tasks. This method is, however, useful in certain special circumstances.
	 * For example 
	 * <ul> 
	 * <li>where a database trigger alters the object state upon insert or update</li>
	 * <li>after executing direct SQL (eg. a mass update) in the same session</li>
	 * <li>after inserting a Blob or Clob</li>
	 * </ul>
	 */
	public void refresh (com.mdss.pos.model.CashDrawerResetHistory cashDrawerResetHistory, Session s)
	{
		refresh((Object) cashDrawerResetHistory, s);
	}


}