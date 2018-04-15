package unisys.test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import unisys.test.models.Job;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = MainApp.class)
@AutoConfigureMockMvc
@Transactional
public class JobDAOTest {

	@Autowired
    private MockMvc mockMvc;
	
	private String exampleInput;
	private String emptyInput;
	
	@Before
	public void setup() {
		exampleInput = new String("{\r\n" + 
				"  \"name\": \"Job Name1\",\r\n" + 
				"  \"active\": false,\r\n" + 
				"  \"tasks\": [\r\n" + 
				"    {\r\n" + 
				"      \"name\": \"J4 First task\",\r\n" + 
				"      \"weight\": 1111,\r\n" + 
				"      \"completed\": true,\r\n" + 
				"      \"createdAt\": \"2015-05-23\"\r\n" + 
				"    },\r\n" + 
				"    {\r\n" + 
				"      \"name\": \"J4 Second task\",\r\n" + 
				"      \"weight\": 1,\r\n" + 
				"      \"completed\": false,\r\n" + 
				"      \"createdAt\": \"2017-05-23\"\r\n" + 
				"    }\r\n" + 
				"  ]\r\n" + 
				"}");
		
		emptyInput  = new String("");
	}
	
    @Test
    public void shouldInsertNewJob() throws Exception {
    	this.mockMvc.perform(post("/tasks")
				   .contentType(MediaType.APPLICATION_JSON)
    		       .content(exampleInput)
    		       .accept(MediaType.APPLICATION_JSON))
    			   .andDo(print())
    		       .andExpect(status().isCreated());
    }
    
//    @Test
//    public void shouldInsertNewJob2() throws Exception {
//    	this.mockMvc.perform(post("/tasks")
//				   .contentType(MediaType.APPLICATION_JSON)
//    		       .content(exampleInput)
//    		       .accept(MediaType.APPLICATION_JSON))
//    			   .andDo(print())
//    		       .andExpect(status().isCreated());
//    }
//    
//    @Test
//    public void shouldReturnDefaultMessage() throws Exception {
//    	this.mockMvc.perform(post("/tasks")
//				   .contentType(MediaType.APPLICATION_JSON)
//    		       .content(exampleInput)
//    		       .accept(MediaType.APPLICATION_JSON))
//    			   .andDo(print())
//    		       .andExpect(status().isCreated());
//    }
//    @Test
//    public void shouldReturnDefaultMessage() throws Exception {
//    	MvcResult result = this.mockMvc.perform(MockMvcRequestBuilders.get("/tasks")).andReturn();
//    	//result.getResponse().getStatus();
//    	System.out.println(result.getResponse().getStatus());
//    	
//        
////        perform(MockMvcRequestBuilders.get("tasks")).andDo(print()).andExpect(status().isOk())
////        	.andExpect(content().string(containsString("Hello World")));
//    }
	
	/**
	 * Testing the function to turn a Json String 
	 * to the object Job. Using the setValuesFromJson method.
	 */
	@Test
	public void testJsonMapper(){
		Job j = new Job();
		j.setValuesFromJson("{\r\n" + 
				"  \"name\": \"Job Name1\",\r\n" + 
				"  \"active\": false,\r\n" + 
				"  \"tasks\": [\r\n" + 
				"    {\r\n" + 
				"      \"name\": \"J4 First task\",\r\n" + 
				"      \"weight\": 1111,\r\n" + 
				"      \"completed\": true,\r\n" + 
				"      \"createdAt\": \"2015-05-23\"\r\n" + 
				"    },\r\n" + 
				"    {\r\n" + 
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
	
//	@Test
//	public void testInsertionToDB() {
//	    Job job = new Job();
//	    job.setValuesFromJson("{\r\n" + 
//				"  \"name\": \"Job Name1\",\r\n" + 
//				"  \"active\": false,\r\n" + 
//				"  \"tasks\": [\r\n" + 
//				"    {\r\n" + 
//				"      \"name\": \"J4 First task\",\r\n" + 
//				"      \"weight\": 1111,\r\n" + 
//				"      \"completed\": true,\r\n" + 
//				"      \"createdAt\": \"2015-05-23\"\r\n" + 
//				"    },\r\n" + 
//				"    {\r\n" + 
//				"      \"name\": \"J4 Second task\",\r\n" + 
//				"      \"weight\": 1,\r\n" + 
//				"      \"completed\": false,\r\n" + 
//				"      \"createdAt\": \"2017-05-23\"\r\n" + 
//				"    }\r\n" + 
//				"  ]\r\n" + 
//				"}");
//	    entityManager.persist(job);
//	    entityManager.flush();
//	 
//	    // when
//	    Job found = jobDAO.get(job.getId());
//	 
//	    // then
//	    assertThat(found.getName()).isEqualTo("Job Name1");
//	    assertThat(found.getName()).isEqualTo(job.getName());
//	}

}
