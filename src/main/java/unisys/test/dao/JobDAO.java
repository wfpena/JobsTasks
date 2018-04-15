package unisys.test.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import unisys.test.models.Job;

/**
 * Domain Repository for Job
 * @author Wilson Pena
 *
 */
@Repository
public class JobDAO extends BaseDAO<Job> {
		
	@PersistenceContext	
	private EntityManager em;
	
	public JobDAO() {
		super(Job.class);
	}
	
	public List<Job> list(boolean order) {
		List<Job> jobs = new ArrayList<Job>();
		if(order != true){
			jobs = em.createQuery("from Job").getResultList();
		}else{
			String hql = new String("select j from Job j "
					+ "inner join j.tasks as t "
					+ "group by j.Id "
					+ "order by sum(t.weight) desc");
			
			jobs = em.createQuery(hql).getResultList();
		}
		return jobs;
	}

}

