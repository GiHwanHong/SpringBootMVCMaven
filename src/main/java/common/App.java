package common;


import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableAutoConfiguration
@EntityScan(basePackages = "common.entity")
@ComponentScan(basePackages = {"common"})
@SpringBootApplication
@EnableTransactionManagement
public class App implements ApplicationRunner{

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SpringApplication.run(App.class,args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {	
	
	}
}
