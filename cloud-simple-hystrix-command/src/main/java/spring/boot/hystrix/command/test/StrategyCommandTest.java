package spring.boot.hystrix.command.test;

import com.netflix.config.ConfigurationManager;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandProperties.ExecutionIsolationStrategy;

/**
 * 隔离策略
 *
 */
public class StrategyCommandTest {
	
	public static void main(String[] args) throws Exception{
		//1.使用线程池的方式
//		ConfigurationManager
//		.getConfigInstance()
//		.setProperty(
//			"hystrix.threadpool.default.coreSize"
//		  , "4");
//		for(int i=0; i < 6; i++){
//			StrategyCommand sc = new StrategyCommand(i);
//			sc.queue();//异步执行，即并发请求
//		}
		
		//2.使用信号量的方式
		ConfigurationManager.getConfigInstance()
			.setProperty("hystrix.command.default.execution.isolation.strategy"
				, ExecutionIsolationStrategy.SEMAPHORE);
		ConfigurationManager.getConfigInstance()
			.setProperty(
				"hystrix.command.default.execution.isolation.semaphore.maxConcurrentRequests", 2);
		for(int i=0; i < 6; i++){//由于信号量外部是没有线程包裹的，直接执行会同步执行
			final int index = i;
			new Thread(() -> {
				StrategyCommand sc = new StrategyCommand(index);
				//这个时候不论怎么执行，都是异步执行的，每个执行命令都被包裹到一个线程里了
				sc.queue();
			}).start();
		}
		Thread.sleep(10000);
	}
	
	static class StrategyCommand extends HystrixCommand<String>{
		
		Integer index;
		
		public StrategyCommand(Integer index){
			super(HystrixCommandGroupKey.Factory.asKey("myGroup"));
			this.index = index;
		}
		
		@Override
		protected String run() throws Exception {
			System.out.println("执行成功："+index);
			return "success";
		}
		
		@Override
		protected String getFallback() {
			System.out.println("执行失败："+index+" 回退");
			return "fail";
		}
		
	}
}