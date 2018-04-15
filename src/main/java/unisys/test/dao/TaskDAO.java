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
	
	public List<Task> list(boolean order) {
		List<Task> tasks = new ArrayList<Task>();

		return tasks;
	}

}
