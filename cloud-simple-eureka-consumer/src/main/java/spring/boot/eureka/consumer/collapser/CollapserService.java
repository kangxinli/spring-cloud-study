package spring.boot.eureka.consumer.collapser;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;

import org.springframework.stereotype.Service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCollapser;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

import spring.boot.eureka.consumer.hystrix.Person;

@Service
public class CollapserService {
 
	@HystrixCollapser(batchMethod = "getPersons", 
			collapserProperties = {
				@HystrixProperty(name = "timerDelayInMilliseconds", value = "10")
			})
	public Future<Person> getPerson(Integer id){
		System.out.println("进入请求收集方法");
		return null;
	}
	
	@HystrixCommand
	public List<Person> getPersons(List<Integer> ids){
		List<Person> persons = new ArrayList<Person>(ids.size());
		System.out.println("====>这是某一次的请求");
		for (Integer id : ids) {
			System.out.println("用户ID："+id);
			Person person = new Person();
			person.setId(id);
			person.setName("spirit"+id);
			persons.add(person);
		}
		return persons;
	}
	
}