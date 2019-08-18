package spring.boot.eureka.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class EurekalClientApplication {
	public static void main(String[] args) {
		SpringApplication.run(EurekalClientApplication.class, args);
	}
}
