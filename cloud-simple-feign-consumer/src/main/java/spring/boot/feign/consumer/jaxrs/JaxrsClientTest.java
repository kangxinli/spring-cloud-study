package spring.boot.feign.consumer.jaxrs;

import feign.Feign;
import feign.jaxrs.JAXRSContract;

public class JaxrsClientTest {
	
	public static void main(String[] args) {
		JaxrsClient client1 = Feign.builder()
				.contract(new JAXRSContract())
				.target(JaxrsClient.class, "http://localhost:8001");
		String result = client1.toHello();
		System.out.println(result);
	}
}
