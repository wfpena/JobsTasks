package unisys.test.controllers;

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

import unisys.test.dao.TaskDAO;
import unisys.test.models.Task;


@RestController
public class TaskController {

	private final Logger logger = Logger.getLogger(TaskController.class);
	
	@Autowired
	private TaskDAO taskDAO;
	
	@RequestMapping(value="/tasks", 
			method=RequestMethod.GET,
		    produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody List<Task> getTasks(@RequestParam("creationDate") String date) {
		logger.info("Ordered? " + date);
		try{
			List<Task> tasks = taskDAO.list(date);
			return tasks;
		}catch(Exception e){
			System.out.println("Erro na transação: " + e);
		}
		return null;
	}
	
	@RequestMapping(value="/tasks", 
			method=RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String jobs(@RequestBody Task task) {
		logger.info("Task:" + task.getId());
		try{
			taskDAO.save(task);
			return "Saved Task Successfully";
		}catch(Exception e){
			logger.info("Transaction Error: " + e);
			return "Failed saving new Task";
		}
    }
//	
//	@RequestMapping(value="/tasks", 
//			method=RequestMethod.GET,
//            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
//    public @ResponseBody List<Job> jobs(@RequestParam("order") boolean order) {
//		logger.info("Ordered? " + order);
//		try{
//			List<Job> jobs = jobDAO.list(order);
//			return jobs;
//		}catch(Exception e){
//			System.out.println("Erro na transação: " + e);
//		}
//		return null;
//    }
//	
//	@RequestMapping(value="/tasks/{id}", 
//			method=RequestMethod.GET,
//            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
//    public @ResponseBody Job jobs(@PathVariable("id") Long id) {
//		logger.info("Retrieving Job with id: " + id);
//		try{
//			Job job = taskDAO.get(id);
//			return job;
//		}catch(Exception e){
//			System.out.println("Erro na transação: " + e);
//		}
//		return null;
//    }
//	
//	@RequestMapping(value="/jobs/{id}", 
//			method=RequestMethod.DELETE,
//            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
//    public String deleteTask(@PathVariable("id") Long id) {
//		logger.info("Deleting Job with id: " + id);
//		try{
//			taskDAO.delete(id);
//			return "Job removed";
//		}catch(Exception e){
//			System.out.println("Erro na transação: " + e);
//		}
//		return "Error removing Job";
//    }
//	
//	@RequestMapping(value="/tasks/{id}", 
//			method=RequestMethod.PUT,
//			consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
//            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
//    public String updateJob(@PathVariable("id") Long id, @RequestBody Job job) {
//		logger.info("Updating job with id " + id);
//		try{
//			jobDAO.update(job);
//			return "Update Success";
//		}catch(Exception e){
//			logger.info("Transaction Error: " + e);
//			return "Failed updating new job";
//		}
//    }
	
	@RequestMapping(value="/tasks/{id}")
    public String getJob(@PathVariable("id")String id) {
        return "ID " + id;
    }
	
}