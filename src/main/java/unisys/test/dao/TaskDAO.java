package unisys.test.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import unisys.test.models.Job;
import unisys.test.models.Task;

@Repository
public class TaskDAO extends BaseDAO<Task> {
		
	@PersistenceContext	
	private EntityManager em;
	
	public TaskDAO() {
		super(Task.class);
	}
	
	public List<Task> list(String creationDate) {
		List<Task> tasks = new ArrayList<Task>();
		if(creationDate != null){
			tasks = em.createQuery("from Task where createdAt like " + creationDate).getResultList();
		} else {
			tasks = em.createQuery("from Task").getResultList();
		}
		return tasks;
	}

}
