package spring.boot.hystrix.command.test;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandProperties;

/**
 * 断路器 测试
 *
 */
public class CircuitBreakerCommandTest {
	
	public static void main(String[] args) throws Exception{
		CircuitBreakerCommand cbc = new CircuitBreakerCommand();
		System.out.println(cbc.execute());
		Thread.sleep(10000);
	}
 
	static class CircuitBreakerCommand extends HystrixCommand<String>{
		
		public CircuitBreakerCommand(){
			super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("myGroup"))
					.andCommandPropertiesDefaults(HystrixCommandProperties.Setter().
							withCircuitBreakerForceOpen(true)));
		}
		
		@Override
		protected String run() throws Exception {
			return "success";
		}
		
		@Override
		protected String getFallback() {
			return "fail";
		}
		
	}
}
