package spring.boot.feign.consumer.jaxrs;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

public interface JaxrsClient {
	
	@GET
	@Path("/hello")
	String toHello();

}
