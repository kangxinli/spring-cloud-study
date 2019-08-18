package spring.boot.feign.consumer;

import feign.Feign;
import feign.gson.GsonEncoder;

public class JsonTest {
	
	public static void main(String[] args) {
		PersonClient client1 = Feign.builder()
				.encoder(new GsonEncoder())
				.target(PersonClient.class, "http://localhost:8001");
		Person person = new Person();
		person.setId(2);
		person.setName("angels");
		String result = client1.savePerson(person);
		System.out.println(result);
	}
}
