package unisys.test.controllers;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
	
	@RequestMapping(value="/tasks/{id}", 
			method=RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public @ResponseBody Task getTask(@PathVariable("id") Long id) {
		logger.info("Getting Taks with ID: " + id);
		System.out.println("AFW");
		try{
			Task task = taskDAO.get(id);
			return task;
		}catch(Exception e){
			logger.info("Transaction Error: " + e);
			//return new ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
			return null;
		}
		
    }
	
	@RequestMapping(value="/tasks/{id}", 
			method=RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String deleteTask(@PathVariable("id") Long id) {
		logger.info("Deleting Job with id: " + id);
		try{
			taskDAO.delete(id);
			return "Task removed successfully";
		}catch(Exception e){
			logger.info("Transaction Error: " + e);
			System.out.println("Erro na transação: " + e);
		}
		return "Error removing Task";
    }
	
	@RequestMapping(value="/tasks/{id}", 
			method=RequestMethod.PUT,
			consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String updateJob(@PathVariable("id") Long id, @RequestBody Task task) {
		logger.info("Updating Task with id " + id);
		try{
			taskDAO.update(task);
			return "Update Success";
		}catch(Exception e){
			logger.info("Transaction Error: " + e);
			return "Failed updating new job";
		}
    }
	
}