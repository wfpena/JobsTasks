package unisys.test.controllers;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import unisys.test.dao.TaskDAO;
import unisys.test.models.Job;
import unisys.test.models.Task;


/**
 * @author Wilson Pena
 *
 */
@RestController
@RequestMapping("/tasks")
public class TaskController {

	private final Logger logger = Logger.getLogger(TaskController.class);
	
	@Autowired
	private TaskDAO taskDAO;
	
	@RequestMapping(value="", method=RequestMethod.GET,
		    produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity getTasks(@RequestParam(value="creationDate", required=false) String date) {
		logger.info(date == null ? "Not filtered by date" : new String("Filtered by date " + date));
		try{
			List<Task> tasks = taskDAO.list(date);
			return ResponseEntity.status(HttpStatus.OK).body(tasks);
		}catch(Exception e){
			logger.error("Transaction Error: " + e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("\"Server Error: " + e.getMessage() +  "\"");
		}
	}
	
	@RequestMapping(value="", method=RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity newTask(@RequestBody Task task) {
		logger.info("Inserting new Task");
		try{
			taskDAO.save(task);
			return ResponseEntity.status(HttpStatus.CREATED).body(task);
		}catch(Exception e){
			logger.info("Transaction Error: " + e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("\"Failed saving new Task: " + e.getLocalizedMessage() + "\"");
		}
    }
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity getTask(@PathVariable("id") Long id) {
		logger.info("Getting Taks with ID: " + id);
		System.out.println("AFW");
		try{
			Task task = taskDAO.get(id);
			return ResponseEntity.status(HttpStatus.OK).body(task);
		}catch(Exception e){
			logger.info("Transaction Error: " + e);
			return ResponseEntity.status(HttpStatus.OK).body("\"Transaction Error\"");
		}
		
    }
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity deleteTask(@PathVariable("id") Long id) {
		logger.info("Deleting Job with ID: " + id);
		try{
			taskDAO.delete(id);
			return ResponseEntity.status(HttpStatus.OK).body("\"Task removed successfully\"");
		}catch(Exception e){
			logger.info("Transaction Error: " + e);
			return ResponseEntity.status(HttpStatus.OK).body("\"Transaction Error\"");
		}
    }
	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT,
			consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity updateJob(@PathVariable("id") Long id, @RequestBody Task task) {
		logger.info("Updating Task with ID: " + id);
		try{
			Task taskUpdate = taskDAO.get(id);
			taskUpdate.setCompleted(task.isCompleted());
			taskUpdate.setCreatedAt(task.getCreatedAt());
			taskUpdate.setJob(task.getJob());
			taskUpdate.setName(task.getName());
			taskUpdate.setWeight(task.getWeight());
			taskDAO.update(taskUpdate);
			return ResponseEntity.status(HttpStatus.OK).body(taskUpdate);
		}catch(Exception e){
			logger.info("Transaction Error: " + e);
			return ResponseEntity.status(HttpStatus.OK).body("\"Transaction Error: " + e.getMessage() + "\"");
		}
    }
	
}