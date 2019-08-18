package spring.boot.feign.consumer.contract;

import feign.Feign;

public class MyContractTest {
	
	public static void main(String[] args) {
		MyContractClient client1 = Feign.builder()
				.contract(new MyContract())	// 使用自定义的注解解释器
				.target(MyContractClient.class, "http://localhost:8001");
		String result = client1.toHello();
		System.out.println(result);
	}
}
