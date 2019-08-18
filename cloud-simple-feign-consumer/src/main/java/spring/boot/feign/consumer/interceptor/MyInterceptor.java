package spring.boot.feign.consumer.interceptor;

import feign.RequestInterceptor;
import feign.RequestTemplate;

public class MyInterceptor implements RequestInterceptor {
	
	@Override
	public void apply(RequestTemplate template) {
		System.out.println("这是我们自定义的请求拦截器");
		//统一设置成json格式的请求类型
		//template.header("Content-type", "application/json");
	}
 
}