package spring.boot.hystrix.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProviderApp {
 
	public static void main(String[] args) {
		SpringApplication.run(ProviderApp.class, args);
	}
 
}