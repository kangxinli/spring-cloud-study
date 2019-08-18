package spring.boot.hystrix.command.test;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;
 
/**
 * 缓存
 *
 */
public class CacheCommandTest {
	
	public static void main(String[] args) {
		HystrixRequestContext context = HystrixRequestContext.initializeContext();//开启一个上下文
		String caCheKey = "myCaCheKey";
		CacheCommand cc1 = new CacheCommand(caCheKey);
		CacheCommand cc2 = new CacheCommand(caCheKey);
		CacheCommand cc3 = new CacheCommand(caCheKey);
		
		cc1.execute();
		cc2.execute();
		cc3.execute();
		
		System.out.println("是否是从缓存中读取的数据："+cc1.isResponseFromCache());
		System.out.println("是否是从缓存中读取的数据："+cc2.isResponseFromCache());
		System.out.println("是否是从缓存中读取的数据："+cc3.isResponseFromCache());
		
		context.shutdown();//关闭上下文
	}
 
	static class CacheCommand extends HystrixCommand<String>{
		String cacheKey;
		public CacheCommand(String cacheKey){
			super(HystrixCommandGroupKey.Factory.asKey("myGroup"));
			this.cacheKey = cacheKey;
		}
		
		@Override
		protected String run() throws Exception {
			System.out.println("执行成功");
			return "success";
		}
		
		@Override
		protected String getFallback() {
			System.out.println("执行失败");
			return "fail";
		}
		
		@Override
		protected String getCacheKey() {
			return this.cacheKey;
		}
	}
	
}