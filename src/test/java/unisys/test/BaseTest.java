package unisys.test;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import unisys.test.controllers.JobController;
import unisys.test.controllers.TaskController;

@RunWith(SpringRunner.class)
@SpringBootTest(classes={MainApp.class})
public class BaseTest {
	
    @Autowired
    private JobController jobController;
    
    @Autowired
    private TaskController taskController;

	@Test
	public void contexLoads() throws Exception {
	    assertThat(jobController).isNotNull();
	    assertThat(taskController).isNotNull();
	}
}
