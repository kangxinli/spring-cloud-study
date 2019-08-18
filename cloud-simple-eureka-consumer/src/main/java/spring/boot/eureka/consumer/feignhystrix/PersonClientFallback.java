package spring.boot.eureka.consumer.feignhystrix;

import org.springframework.context.annotation.Configuration;

import spring.boot.eureka.consumer.hystrix.Person;

@Configuration
public class PersonClientFallback implements PersonClient {
 
	@Override
	public Person getPersonById(Integer id) {
		Person person = new Person();
		person.setId(id);
		person.setName("angels");
		return person;
	}
 
}