package unisys.test;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.cfg.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * @author Wilson Pena
 *
 */
@EnableAutoConfiguration
@EnableWebMvc
@Configuration
@ComponentScan
public class MainApp extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(MainApp.class, args);
	}
	
}
