package spring.boot.hystrix.command.test;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;

import rx.Observable;
import rx.Observer;

public class CommandRunTest {

	public static void main(String[] args) throws Exception {
		LaunchCommand cr1 = new LaunchCommand("observe");
		cr1.observe();
		
		LaunchCommand cr2 = new LaunchCommand("toObservable");
		Observable<String> returnOb = cr2.toObservable();
		returnOb.subscribe(new Observer<Object>() {
		 
			@Override
			public void onCompleted() {
				System.out.println("执行完成");
			}
		 
			@Override
			public void onError(Throwable e) {
				System.out.println("执行发生错误");
				e.printStackTrace();
			}
		 
			@Override
			public void onNext(Object t) {
				System.out.println("执行返回结果：" + t);
			}
		});
		Thread.sleep(10000);
	}

	static class LaunchCommand extends HystrixCommand<String> {

		String invokerName;

		public LaunchCommand(String invokerName) {
			super(HystrixCommandGroupKey.Factory.asKey("myGroup"));
			this.invokerName = invokerName;
		}

		@Override
		protected String run() throws Exception {
			System.out.println("调用方法：" + invokerName);
			return "执行成功";
		}

		@Override
		protected String getFallback() {
			return "执行失败";
		}
	}
}
