package spring.boot.feign.consumer.interceptor;

import feign.Feign;
import spring.boot.feign.consumer.PersonClient;

public class MyInterceptorTest {
	 
	public static void main(String[] args) {
		PersonClient client1 = Feign.builder()
				.requestInterceptor(new MyInterceptor())//使用自定义拦截器
				.target(PersonClient.class, "http://localhost:8001");
		String result = client1.toHello();
		System.out.println(result);
	}
	
}