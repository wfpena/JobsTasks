package unisys.test.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Wilson Pena
 *
 * @param <T> Generic type for the DAO data type
 */
public abstract class BaseDAO <T> {
	private Class<T> clazz;
 
	@PersistenceContext
	private EntityManager em;
	
	public BaseDAO(Class<T> clazz){
		this.clazz = clazz;
	}
	
	@Transactional
	public void save(final T entity) {
		em.merge(entity);
	}
	
	@Transactional
	public T get(final int id) {
		final T entity = em.find(clazz, id);
		return entity;
	}
	
	@Transactional
	public void delete(final T entity) {
		em.remove(entity);
	}
	
	@Transactional
	public void delete(final int id) {
		T entity = get(id);
		em.remove(entity);
	}
	
	@Transactional
	public T update(final T entity) {
		return (T) em.merge(entity);
	}
}
