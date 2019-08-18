package spring.boot.eureka.consumer.hystrix;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service
public class PersonService {
	
	@Autowired
	private RestTemplate restTemplate;
 
	/**
	 * Hystrix的常用配置
	
	@HystrixCommand(fallbackMethod = "getPersonFallback", groupKey = "myGroupKey", 
		commandKey = "myCommandKey",
		commandProperties = {
			@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "500")
		},
		threadPoolProperties = {
			@HystrixProperty(name = "coreSize", value = "5")
		})

	 */
	@HystrixCommand(fallbackMethod = "getPersonFallback")
	public Person getPersonById(Integer id){
		return restTemplate.getForObject("http://eureka-client/search/{id}", Person.class, id);
	};
	
	/**
	 * 停止eureka-client项目的服务
	 * 
	 * 访问http://localhost:9000/router/1
	 * 
	 * 可以看到请求未成功，走了回退逻辑
	 * 
	 * @param id
	 * @return
	 */
	public Person getPersonFallback(Integer id){
		Person person = new Person();
		person.setId(id);
		person.setName("angels");
		return person;
	}
	
}