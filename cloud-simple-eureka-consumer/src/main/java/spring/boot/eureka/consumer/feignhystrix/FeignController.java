package spring.boot.eureka.consumer.feignhystrix;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import spring.boot.eureka.consumer.hystrix.Person;

@RestController
public class FeignController {
 
	@Autowired
	private PersonClient personClient;
	
	@RequestMapping(value = "/feign/{id}", method = RequestMethod.GET, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public Person getPersonById(@PathVariable Integer id){
		return personClient.getPersonById(id);
	}
	
}