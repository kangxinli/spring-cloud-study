package spring.boot.eureka.client.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProviderController {
 
	@RequestMapping(value = "/search/{id}", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Person searchPerson(@PathVariable Integer id,HttpServletRequest request){
		Person person = new Person();
		person.setId(id);
		person.setName("Spirit");
		person.setMessage(request.getRequestURL().toString());
		return person;
	}
	
	@RequestMapping(value = "/hello", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String hello(){
		return "hello world!";
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public String savePerson(@RequestBody Person person){
		System.out.println("需要存储的person对象"+person);
		return "success";
	}
}
