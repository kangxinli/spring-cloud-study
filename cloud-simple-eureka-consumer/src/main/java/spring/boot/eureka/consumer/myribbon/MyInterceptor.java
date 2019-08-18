package spring.boot.eureka.consumer.myribbon;

import java.io.IOException;

import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

public class MyInterceptor implements ClientHttpRequestInterceptor {
 
//	@Override
//	public ClientHttpResponse intercept(HttpRequest request, byte[] body,
//			ClientHttpRequestExecution execute) throws IOException {
//		System.out.println("这是我们自定义的拦截器");
//		System.out.println("请求的URI地址是："+request.getURI());
//		return execute.execute(request, body);
//	}
	
	@Override
	public ClientHttpResponse intercept(HttpRequest request, byte[] body,
			ClientHttpRequestExecution execute) throws IOException {
		System.out.println("这是我们自定义的拦截器");
		System.out.println("请求的URI地址是："+request.getURI());
		HttpRequest myHttpRequest = new MyHttpRequest(request);
		return execute.execute(myHttpRequest, body);
	}
}
