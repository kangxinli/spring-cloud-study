package spring.boot.zuul.router;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableZuulProxy
public class RouterApp {
 
	public static void main(String[] args) {
		SpringApplication.run(RouterApp.class, args);
	}
	
}
