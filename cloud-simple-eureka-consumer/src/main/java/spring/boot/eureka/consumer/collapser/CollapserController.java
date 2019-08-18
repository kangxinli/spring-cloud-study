package spring.boot.eureka.consumer.collapser;

import java.util.concurrent.Future;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import spring.boot.eureka.consumer.hystrix.Person;

/**
 * 请求合并
 * 
 */
@RestController
public class CollapserController {
	
	@Autowired
	private CollapserService collapserService;
	
	@RequestMapping(value = "/collapser", method = RequestMethod.GET, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public String getPerson() throws Exception{
		Future<Person> f1 = collapserService.getPerson(1);
		Future<Person> f2 = collapserService.getPerson(2);
		Future<Person> f3 = collapserService.getPerson(3);
		
		Person p1 = f1.get();
		Person p2 = f2.get();
		Person p3 = f3.get();
		
		System.out.println(p1);
		System.out.println(p2);
		System.out.println(p3);
		return "success";
	}
	
}