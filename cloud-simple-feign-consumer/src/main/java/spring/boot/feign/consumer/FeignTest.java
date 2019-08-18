package spring.boot.feign.consumer;

import feign.Feign;
import feign.gson.GsonDecoder;

/**
 * feign 测试类
 *
 */
public class FeignTest {
	public static void main(String[] args) {
		/**
		 * 1:启动cloud-simple-eureka
		 * 2:启动cloud-simple-eureka-client
		 * 3:访问ProviderController接口
		 */
		//1.简单字符串返回值
		PersonClient client1 = Feign.builder()
				.target(PersonClient.class, "http://localhost:8001");
		String result = client1.toHello();
		System.out.println(result);
		
		//2.返回一个对象
		PersonClient client2 = Feign.builder().decoder(new GsonDecoder())
				.target(PersonClient.class, "http://localhost:8001");
		Person person = client2.getPersonById(1);
		System.out.println(person);
	}
}
