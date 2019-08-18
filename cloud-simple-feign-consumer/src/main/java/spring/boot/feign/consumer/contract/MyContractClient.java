package spring.boot.feign.consumer.contract;

public interface MyContractClient {
	
	@MyRequest(url = "/hello",method = "GET")
	String toHello();
}
