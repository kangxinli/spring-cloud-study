package spring.boot.feign.consumer.logger;

import feign.Feign;
import feign.Logger;
import spring.boot.feign.consumer.PersonClient;

public class LoggerTest {
	 
	public static void main(String[] args) {
		PersonClient client1 = Feign.builder()
				.logLevel(Logger.Level.FULL)//这里是日志的输出等级
				.logger(new Logger.JavaLogger().appendToFile("logs/http.log"))
				.target(PersonClient.class, "http://localhost:8001");
		String result = client1.toHello();
		System.out.println(result);
	}
	
}