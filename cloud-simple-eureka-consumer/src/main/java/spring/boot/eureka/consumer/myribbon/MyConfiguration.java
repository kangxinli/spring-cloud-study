package spring.boot.eureka.consumer.myribbon;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.web.client.RestTemplate;

@Configuration
public class MyConfiguration {
 
	@Autowired(required = false)
	@MyLoadBalanced
	private List<RestTemplate> tpls = Collections.emptyList();
	
	
	// 在初始化之后去创建一个实例bean
	@Bean
	public SmartInitializingSingleton loadBalanceInit(){
		return () -> {
			System.out.println("被@MyLoadBalanced修饰的RestTemplate Bean的数目："+tpls.size());
			for (RestTemplate tpl : tpls) {
				List<ClientHttpRequestInterceptor> interceptors = tpl.getInterceptors();
				MyInterceptor myInterceptor = new MyInterceptor();
				interceptors.add(myInterceptor);
			}
		};
	}
}