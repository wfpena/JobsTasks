package unisys.test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;

import junit.framework.Assert;
import unisys.test.models.Job;
import unisys.test.models.Task;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = MainApp.class)
@AutoConfigureMockMvc
@Transactional
public class JobsAPITest {

	@Autowired
    private MockMvc mockMvc;
	
	private String exampleInput;
	private String emptyInput;
	
	@Before
	public void setup() {
		exampleInput = new String("{\r\n" + 
				"  \"id\": 1,\r\n" + 
				"  \"name\": \"dawdwa\",\r\n" + 
				"  \"active\": false,\r\n" + 
				"  \"tasks\": [\r\n" + 
				"    {\r\n" + 
				"    	 \"id\": 1,\r\n" + 
				"      \"name\": \"J4 First task\",\r\n" + 
				"      \"weight\": 1111,\r\n" + 
				"      \"completed\": true,\r\n" + 
				"      \"createdAt\": \"2015-05-23\"\r\n" + 
				"    },\r\n" + 
				"    {\r\n" + 
				"    	 \"id\":2,\r\n" + 
				"      \"name\": \"J4 Second task\",\r\n" + 
				"      \"weight\": 1,\r\n" + 
				"      \"completed\": false,\r\n" + 
				"      \"createdAt\": \"2017-05-23\"\r\n" + 
				"    }\r\n" + 
				"  ]\r\n" + 
				"}");
		
		emptyInput  = new String("");
	}
	
	/**
	 * Testing the function to turn a Json String 
	 * to the object Job. Using the setValuesFromJson method.
	 */
	@Test
	public void jsonMapperShouldWork(){
		Job j = new Job();
		j.setValuesFromJson("{\r\n" + 
				"  \"id\": 1,\r\n" + 
				"  \"name\": \"Job Name1\",\r\n" + 
				"  \"active\": false,\r\n" + 
				"  \"tasks\": [\r\n" + 
				"    {\r\n" + 
				"    	 \"id\": 1,\r\n" + 
				"      \"name\": \"J4 First task\",\r\n" + 
				"      \"weight\": 1111,\r\n" + 
				"      \"completed\": true,\r\n" + 
				"      \"createdAt\": \"2015-05-23\"\r\n" + 
				"    },\r\n" + 
				"    {\r\n" + 
				"    	 \"id\":2,\r\n" + 
				"      \"name\": \"J4 Second task\",\r\n" + 
				"      \"weight\": 1,\r\n" + 
				"      \"completed\": false,\r\n" + 
				"      \"createdAt\": \"2017-05-23\"\r\n" + 
				"    }\r\n" + 
				"  ]\r\n" + 
				"}");
		
		assertThat(j.getActive()).isEqualTo(false);
		assertThat(j.getName()).isEqualTo("Job Name1");
		assertThat(j.getTasks().size()).isEqualTo(2);
		
	}
	
    @Test
    public void shouldInsertNewJob() throws Exception {
    	this.mockMvc.perform(post("/jobs")
				   .contentType(MediaType.APPLICATION_JSON)
    		       .content(exampleInput)
    		       .accept(MediaType.APPLICATION_JSON))
    			   .andDo(print())
    		       .andExpect(status().isCreated());
    }
    
    @Test
    public void shouldGetListOfJobs() throws Exception {
    	this.mockMvc.perform(post("/jobs")
				   .contentType(MediaType.APPLICATION_JSON)
    		       .content(exampleInput)
    		       .accept(MediaType.APPLICATION_JSON))
    			   .andDo(print())
    		       .andExpect(status().isCreated());
    	
    	MvcResult result = this.mockMvc.perform(get("/jobs")
				   .contentType(MediaType.APPLICATION_JSON)
    		       .content(exampleInput)
    		       .accept(MediaType.APPLICATION_JSON))
    			   .andDo(print())
    		       .andExpect(status().isOk())
    		       .andReturn();
    	
    	try {
        	ObjectMapper mapper = new ObjectMapper();
    		Job[] jobs = mapper.readValue(result.getResponse().getContentAsString(), Job[].class);
    		assertThat(jobs.length).isEqualTo(1); 	
 	    }
 	    catch (Exception e) {
 	      Assert.fail("Exception " + e);
 	    }
    }
    
    @Test
    public void shouldDeleteJobById() throws Exception {
    	this.mockMvc.perform(post("/jobs")
				   .contentType(MediaType.APPLICATION_JSON)
 		       .content(exampleInput)
 		       .accept(MediaType.APPLICATION_JSON))
 			   .andDo(print())
 		       .andExpect(status().isCreated());
    	    	
    	this.mockMvc.perform(delete("/jobs/1")
				   .contentType(MediaType.APPLICATION_JSON)
    		       .content(exampleInput)
    		       .accept(MediaType.APPLICATION_JSON))
    			   .andDo(print())
    		       .andExpect(status().isOk());
    }
    
    @Test
    public void shouldntInserAsParentOfItself() throws Exception {
    	this.mockMvc.perform(post("/jobs")
			   .contentType(MediaType.APPLICATION_JSON)
 		       .content(exampleInput)
 		       .accept(MediaType.APPLICATION_JSON))
 			   .andDo(print())
 		       .andExpect(status().isCreated());
    	    	
    }


}
