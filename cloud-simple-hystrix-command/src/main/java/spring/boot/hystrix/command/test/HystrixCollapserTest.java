package spring.boot.hystrix.command.test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.Future;
 
import com.netflix.hystrix.HystrixCollapser;
import com.netflix.hystrix.HystrixCollapserKey;
import com.netflix.hystrix.HystrixCollapserProperties;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixEventType;
import com.netflix.hystrix.HystrixRequestLog;
import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;

/**
 * 请求合并
 *
 */
public class HystrixCollapserTest {
	
	public static void main(String[] args) throws Exception{
        HystrixRequestContext context = HystrixRequestContext.initializeContext();
        try {
            Future<String> f1 = new UserCommandCollapser(1).queue();
            Future<String> f2 = new UserCommandCollapser(2).queue();
            Future<String> f3 = new UserCommandCollapser(3).queue();
            Future<String> f4 = new UserCommandCollapser(4).queue();
 
            System.out.println(f1.get());
            System.out.println(f2.get());
            System.out.println(f3.get());
            System.out.println(f4.get());
            
            //查看当前请求所有已执行命令数的大小
            System.out.println(HystrixRequestLog.getCurrentRequest().getAllExecutedCommands().size());
            HystrixCommand<?> command = HystrixRequestLog.getCurrentRequest().getAllExecutedCommands().toArray(new HystrixCommand<?>[1])[0];
 
            System.out.println("已执行command的commandKey："+command.getCommandKey().name());
            System.out.println("请求是否被合并了："+command.getExecutionEvents().contains(HystrixEventType.COLLAPSED));
            System.out.println("请求是否成功了："+command.getExecutionEvents().contains(HystrixEventType.SUCCESS));
        } finally {
            context.shutdown();
        }
	}
	
	//	- BatchReturnType：createCommand()方法创建批量命令的返回值的类型。 
	//	- ResponseType：单个请求返回的类型。 
	//	- RequestArgumentType：getRequestArgument()方法请求参数的类型。
	static class UserCommandCollapser extends HystrixCollapser<List<String>, String, Integer>{
 
		final Integer index;
		
		public UserCommandCollapser(Integer index){
			//设置批处理请求合并的最小时间范围
			super(Setter.withCollapserKey(HystrixCollapserKey.Factory.asKey("myCollapserKey"))
					.andCollapserPropertiesDefaults(
							HystrixCollapserProperties.Setter().withTimerDelayInMilliseconds(100)));
			this.index = index;
		}
		
		@Override
		public Integer getRequestArgument() {//获取请求参数
			return this.index;
		}
 
		//合并请求产生批量命令
		@Override
		protected HystrixCommand<List<String>> createCommand(
				Collection<com.netflix.hystrix.HystrixCollapser.CollapsedRequest<String, Integer>> requests) {
			return new BatchCommand(requests);
		}
 
		//批量命令执行后的返回结果
		@Override
		protected void mapResponseToRequests(
				List<String> batchResponse,
				Collection<com.netflix.hystrix.HystrixCollapser.CollapsedRequest<String, Integer>> requests) {
			int count = 0;
	        for (CollapsedRequest<String, Integer> request : requests) {
	            request.setResponse(batchResponse.get(count++));
	        }
		}
		
		private static final class BatchCommand extends HystrixCommand<List<String>> {
	        private final Collection<CollapsedRequest<String, Integer>> requests;
	 
	        private BatchCommand(Collection<CollapsedRequest<String, Integer>> requests) {
	                super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("myGroupKey"))
	                    .andCommandKey(HystrixCommandKey.Factory.asKey("myCommandKey")));
	            this.requests = requests;
	        }
	 
	        @Override
	        protected List<String> run() {
	            ArrayList<String> response = new ArrayList<String>();
	            for (CollapsedRequest<String, Integer> request : requests) {
	                // 批量收到的每个参数的响应
	                response.add("collapser: " + request.getArgument());
	            }
	            return response;
	        }
	    }
		
	}
}