package unisys.test.controllers;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import unisys.test.dao.JobDAO;
import unisys.test.dao.TaskDAO;
import unisys.test.models.Job;
import unisys.test.models.Task;


/**
 * @author Wilson Pena
 *
 */
@RestController
@RequestMapping("/jobs")
public class JobController {

	private final Logger logger = Logger.getLogger(JobController.class);
	
	@Autowired
	private JobDAO jobDAO;
	
	@Autowired
	private TaskDAO taskDAO;
	
	DateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");
	
	@RequestMapping(value="", method=RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity newJob(@RequestBody Job job) {
		logger.info("Saving new Job");
		try{
			if(isParent(job, job.getId())){
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("\"Invalid Job: It is already in this chain\"");
			} else {
				List<Task> tasks = new ArrayList<Task>();
				for(Task task : job.getTasks()){
					Task t = taskDAO.get(task.getId());
					if(t != null){
						t.setJob(job);
						taskDAO.update(t);
						tasks.add(t);
					} else {
						task.setJob(job);
						taskDAO.save(task);
						tasks.add(task);
					}
				}
				job.setTasks(tasks);
				
				if(job.getParentJob() != null){
					if(jobDAO.get(job.getParentJob().getId()) == null){
						jobDAO.save(job.getParentJob());
					} 
					job.setParentJob(jobDAO.get(job.getParentJob().getId()));
					
				}
				jobDAO.save(job);
				logger.info("Job Saved Successfully");
				return ResponseEntity.status(HttpStatus.CREATED).body(job);
			}
		}catch(Exception e){
			logger.info("Transaction Error: " + e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("\"Transaction Error: " + e.getMessage() + "\"");
		}
    }
	
	
	/**
	 * Helper function to check if the Job is a parent of itself
	 * in the hierarchy chain
	 * @param job
	 * @param id
	 * @return boolean
	 */
	public boolean isParent(Job job, int id){
		try{
			if(job.getParentJob() == null){
				return false;
			} else if(job.getParentJob().getId() == id) {
				return true;
			} else if(jobDAO.get(job.getParentJob().getId()) == null){
				return false;
			}else {
				return isParent(jobDAO.get(job.getParentJob().getId()), id);
			}
		} catch(NullPointerException e){
			return false;
		}
		
	}
	
	@RequestMapping(value="", method=RequestMethod.GET,
            produces="application/json")
    public ResponseEntity getJobs(@RequestParam(value="order", required=false) boolean order) {
		logger.info("Retrieving All Jobs");
		logger.info(order ? "Sorted by sum of Taks weight": "Not sorted by sum of Tasks weight");
		try{
			List<Job> jobs = jobDAO.list(order);
			logger.info("Retrieved " + jobs.size() + " Jobs");
			return ResponseEntity.status(HttpStatus.OK).body(jobs);
		}catch(Exception e){
			logger.error("Transaction Error: " + e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("\"Transaction Error\"");
		}
    }
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity getJob(@PathVariable("id") int id) {
		logger.info("Retrieving Job with ID: " + id);
		try{
			Job job = jobDAO.get(id);
			logger.info("End of Transaction");
			if(job != null){
				return ResponseEntity.status(HttpStatus.OK).body(job);
			} else {
				return new ResponseEntity<String>("\"Job not Found\"", HttpStatus.NOT_FOUND);
			}
		}catch(Exception e){
			logger.error("Transaction Error: " + e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("\"Internal Error retrieving Job\"");
		}
    }
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity deleteJob(@PathVariable("id") int id) {
		logger.info("Deleting Job with id: " + id);
		try{
			jobDAO.delete(id);
			logger.info("End of Transaction");
			return ResponseEntity.status(HttpStatus.OK).body("");
		}catch(Exception e){
			logger.error("Transaction Error: " + e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("\"Error removing Job: "+ e.getMessage() + "\"");
		}
    }
	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT,
			consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity updateJob(@PathVariable("id") int id, @RequestBody Job job) {
		logger.info("Updating job with id " + id);
		try{
			Job jobUpdate = jobDAO.get(id);
			jobUpdate.setActive(job.getActive());
			jobUpdate.setChildJobs(job.getChildJobs());
			jobUpdate.setName(job.getName());
			jobUpdate.setParentJob(job.getParentJob());
			jobUpdate.setTasks(job.getTasks());
			jobDAO.update(jobUpdate);
			return ResponseEntity.status(HttpStatus.OK).body(jobUpdate);
		}catch(Exception e){
			logger.info("Transaction Error: " + e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("\"Error: "+ e.getMessage() + "\"");
		}
    }
	
}