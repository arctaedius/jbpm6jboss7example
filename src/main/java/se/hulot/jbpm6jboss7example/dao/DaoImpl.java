package se.hulot.jbpm6jboss7example.dao;

import java.lang.reflect.ParameterizedType;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.jboss.solder.logging.Logger;

public class DaoImpl<T> implements Dao<T> {
	private final Class<T> type;

	@PersistenceContext(unitName = "se.hulot.jbpm6jboss7example")
	protected EntityManager em;
	
	private static Logger log = Logger.getLogger(DaoImpl.class);
	
	@SuppressWarnings("unchecked")
	public DaoImpl() {
		type = (Class<T>)((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}

	public T create(T t) {
		log.debug("creating " + t.getClass().getSimpleName());
		em.persist(t);
		return t;
	}

	public T find(Object id) {
		log.debug("find " + type.getClass().getSimpleName() + " with id:" + id);
		return em.find(type, id);
	}

	public T update(T t) {
		log.debug("update " + type.getClass().getSimpleName());
		return em.merge(t);
	}

	public void delete(Object id) {
		log.debug("delete " + type.getClass().getSimpleName());
		T t = em.getReference(type, id);
		em.remove(t);
		em.flush();
	}
}
