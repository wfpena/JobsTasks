package unisys.test;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

import unisys.test.MainApp;
import unisys.test.dao.JobDAO;
import unisys.test.models.Job;

@RunWith(SpringRunner.class)
//@ContextConfiguration(locations = { "classpath:application.properties" })
//@DataJpaTest
//@ComponentScan(basePackages = "unisys.test")
@SpringBootTest(classes = MainApp.class)
public class JobDAOTest {
	    
    @Autowired
    private TestEntityManager entityManager;

	@Autowired
	private JobDAO jobDAO;

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
	
	@Test
	public void testInsertionToDB() {
	    Job job = new Job();
	    job.setValuesFromJson("{\r\n" + 
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
	    entityManager.persist(job);
	    entityManager.flush();
	 
	    // when
	    Job found = jobDAO.get(job.getId());
	 
	    // then
	    assertThat(found.getName()).isEqualTo("Job Name1");
	    assertThat(found.getName()).isEqualTo(job.getName());
	}

}
