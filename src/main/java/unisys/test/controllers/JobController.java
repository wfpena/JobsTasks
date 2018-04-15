package unisys.test.controllers;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import unisys.test.dao.JobDAO;
import unisys.test.models.Job;
import unisys.test.models.Task;


@RestController
public class JobController {

	private final Logger logger = Logger.getLogger(JobController.class);
	
	@Autowired
	private JobDAO jobDAO;
	
	DateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");
	
	@RequestMapping(value="/jobs", 
			method=RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String jobs(@RequestBody Job job) {
		logger.info("Saving new Job");
		try{
			for(Task task : job.getTasks()){
				task.setJob(job);
			}
			jobDAO.save(job);
			logger.info("Job Saved Successfully");
			return "Saved Success";
		}catch(Exception e){
			logger.info("Transaction Error: " + e);
			return "Failed saving new job";
		}
    }
	
	@RequestMapping(value="/jobs", 
			method=RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public @ResponseBody List<Job> jobs(@RequestParam(value="order", required=false) boolean order) {
		logger.info("Retrieving All Jobs");
		logger.info(order ? "Sorted by sum of Taks weight": "Not sorted by sum of Tasks weight");
		try{
			List<Job> jobs = jobDAO.list(order);
			return jobs;
			//return new ResponseEntity<List<Job>>(jobs, HttpStatus.OK);
		}catch(Exception e){
			System.out.println("Erro na transação: " + e);
			//return new ResponseEntity<String>("Erro: :" + e, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return null;
    }
	
	@RequestMapping(value="/jobs/{id}", 
			method=RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public @ResponseBody Job jobs(@PathVariable("id") Long id) {
		logger.info("Retrieving Job with id: " + id);
		try{
			Job job = jobDAO.get(id);
			return job;
		}catch(Exception e){
			System.out.println("Erro na transação: " + e);
		}
		return null;
    }
	
	@RequestMapping(value="/jobs/{id}", 
			method=RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String deleteJob(@PathVariable("id") Long id) {
		logger.info("Deleting Job with id: " + id);
		try{
			jobDAO.delete(id);
			return "Job removed";
		}catch(Exception e){
			System.out.println("Erro na transação: " + e);
		}
		return "Error removing Job";
    }
	
	@RequestMapping(value="/jobs/{id}", 
			method=RequestMethod.PUT,
			consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String updateJob(@PathVariable("id") Long id, @RequestBody Job job) {
		logger.info("Updating job with id " + id);
		try{
			Job jobUpdate = jobDAO.get(id);
			jobUpdate.setActive(job.getActive());
			jobUpdate.setChildJobs(job.getChildJobs());
			jobUpdate.setName(job.getName());
			jobUpdate.setParentJob(job.getParentJob());
			jobUpdate.setTasks(job.getTasks());
			jobDAO.update(jobUpdate);
			return "Update Success";
		}catch(Exception e){
			logger.info("Transaction Error: " + e);
			return "Failed updating new job";
		}
    }
	
}