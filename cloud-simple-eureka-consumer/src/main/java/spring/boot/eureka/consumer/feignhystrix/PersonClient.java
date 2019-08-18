package spring.boot.eureka.consumer.feignhystrix;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import spring.boot.eureka.consumer.hystrix.Person;

@FeignClient(name = "eureka-client", fallback = PersonClientFallback.class)
public interface PersonClient {
	
	@RequestMapping(value = "/search/{id}", method = RequestMethod.GET,
			consumes = "application/json")
	public Person getPersonById(@PathVariable("id") Integer id);
	
}