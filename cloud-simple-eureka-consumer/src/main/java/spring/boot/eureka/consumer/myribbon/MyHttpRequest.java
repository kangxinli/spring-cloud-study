package spring.boot.eureka.consumer.myribbon;

import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpRequest;

public class MyHttpRequest implements HttpRequest {
	 
	private HttpRequest httpRequest;
	
	public MyHttpRequest(HttpRequest httpRequest){
		this.httpRequest = httpRequest;
	}
	
	@Override
	public HttpHeaders getHeaders() {
		return httpRequest.getHeaders();
	}
 
	@Override
	public HttpMethod getMethod() {
		return httpRequest.getMethod();
	}
 
	@Override
	public URI getURI() {
		try {
			URI myURI = new URI("http://localhost:9000/forward");
			return myURI;
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		return httpRequest.getURI();
	}
 
}