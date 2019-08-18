package spring.boot.feign.consumer.httpClient;

import feign.Feign;
import spring.boot.feign.consumer.PersonClient;

public class MyClientTest {
	 
	public static void main(String[] args) {
		//自定义Feign客户端测试
		PersonClient client1 = Feign.builder()
				.client(new MyClient())
				.target(PersonClient.class, "http://localhost:8001");
		String result = client1.toHello();
		System.out.println(result);
	}
	
}
