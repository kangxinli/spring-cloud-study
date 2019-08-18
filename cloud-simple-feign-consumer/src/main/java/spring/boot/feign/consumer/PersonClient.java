package spring.boot.feign.consumer;

import feign.Headers;
import feign.Param;
import feign.RequestLine;

public interface PersonClient {
	
	@RequestLine("GET /hello")
	String toHello();
	
	@RequestLine("GET /search/{id}")
	Person getPersonById(@Param("id") Integer id);
	
	@RequestLine("POST /save")
	@Headers("Content-type: application/json")
	String savePerson(Person person);
}
