package spring.boot.eureka.zuul.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {
 
	@RequestMapping(value = "/sayHi/{name}", method = RequestMethod.GET)
	public String sendForword(@PathVariable String name){
		return "Hi，"+name;
	}
	
}