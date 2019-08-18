package spring.boot.eureka.consumer.myribbon;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@Configuration
public class MyController {
 
	@Bean
	@MyLoadBalanced
	public RestTemplate MyRestTemplate(){
		return new RestTemplate();
	}
	
	@GetMapping(value = "/router")
	@ResponseBody
	public String router(){
		RestTemplate temp = MyRestTemplate();
		return temp.getForObject("http://localhost:9000/invoke", String.class);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/invoke", produces = MediaType.APPLICATION_JSON_VALUE)
	public String invoke(){
		return "调用invoke方法";
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/forward", produces = MediaType.APPLICATION_JSON_VALUE)
	public String forward(){
		return "调用forward方法";
	}
}