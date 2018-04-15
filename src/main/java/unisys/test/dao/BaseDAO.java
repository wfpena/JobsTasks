package unisys.test.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Transactional;

public abstract class BaseDAO <T> {
	private Class<T> clazz;
 
	@PersistenceContext
	private EntityManager em;
	
	public BaseDAO(Class<T> clazz){
		this.clazz = clazz;
	}
	
	@Transactional
	public void save(final T entity) {
		em.persist(entity);
	}
	
	@Transactional
	public T get(final Long id) {
		final T entity = em.find(clazz, id);
		return entity;
	}
	
	@Transactional
	public void delete(final T entity) {
		em.remove(entity);
	}
	
	@Transactional
	public void delete(final Long id) {
		T entity = get(id);
		em.remove(entity);
	}
	
	@Transactional
	public T update(final T entity) {
		return (T) em.merge(entity);
	}
 
//   public List<T> findAll() {
//	   return getCurrentSession().createQuery("from " + clazz.getName()).list();
//   }
//
// 
//   public T update(final T entity) {
//      return (T) getCurrentSession().merge(entity);
//   }
// 
//   public void delete(final T entity) {
//      getCurrentSession().delete(entity);
//   }
//   
//   public void deleteById(final long id) {
//      final T entity = findOne(id);
//      delete( entity );
//   }
// 
//   protected final Session getCurrentSession() {
//      return sessionFactory.getCurrentSession();
//   }
}
