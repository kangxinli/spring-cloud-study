package spring.boot.eureka.consumer.cache;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;

@WebFilter(urlPatterns = "/*", filterName = "HystrixFilter")
public class MyFilter implements Filter {
 
	@Override
	public void destroy() {
 
	}
 
	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HystrixRequestContext context = HystrixRequestContext.initializeContext();//开启一个上下文
		try {
			System.out.println("这里是过滤器");
			chain.doFilter(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			context.shutdown();
		}
	}
 
	@Override
	public void init(FilterConfig arg0) throws ServletException {
 
	}
 
}