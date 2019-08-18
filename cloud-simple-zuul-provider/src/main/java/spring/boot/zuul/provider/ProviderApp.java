package spring.boot.zuul.provider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class ProviderApp {
 
	public static void main(String[] args) {
		SpringApplication.run(ProviderApp.class, args);
	}
	
	@RequestMapping(value = "/msg/{name}", method = RequestMethod.GET, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public String getMessage(@PathVariable String name){
		return "你好，"+name+"!";
	}
	
}