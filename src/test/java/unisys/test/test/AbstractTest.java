package unisys.test.test;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import unisys.test.MainApp;
import unisys.test.controllers.JobController;

@RunWith(SpringRunner.class)
@SpringBootTest(classes={MainApp.class})
//@Transactional // To rollback Junit
public class AbstractTest {
	
    @Autowired
    private JobController jobController;
    
//    @LocalServerPort
//    private int port;
//
//    @Autowired
//    private TestRestTemplate restTemplate;
    
	@Test
  public void contextLoads() throws Exception {
		assertThat(jobController).isNotNull();
  }

//    @Test
//    public void greetingShouldReturnDefaultMessage() throws Exception {
//        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/jobs",
//                String.class)).contains("");
//    }

//	@Test
//    public void contextLoads() throws Exception {
//		assertThat("JobController is not null", jobController, isNotNull());
//    }
}
