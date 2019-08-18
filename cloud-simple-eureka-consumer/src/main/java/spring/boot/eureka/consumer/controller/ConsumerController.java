package spring.boot.eureka.consumer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.netflix.ribbon.SpringClientFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.netflix.loadbalancer.DynamicServerListLoadBalancer;
import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.Server;

@RestController
public class ConsumerController {

	@Autowired
	RestTemplate restTemplate;

	@Autowired
	private DiscoveryClient discoveryClient;

	@RequestMapping(value = "/eureka-consumer", method = RequestMethod.GET)
	public String helloConsumer() {
		return restTemplate.getForEntity("http://eureka-client/search/1", String.class).getBody();
	}

	@GetMapping(value = "/count")
	@ResponseBody
	public String countService() {
		List<String> services = discoveryClient.getServices();
		for (String string : services) {
			List<ServiceInstance> instances = discoveryClient.getInstances(string);
			System.out.println("服务名称：" + string + ",服务数量：" + instances.size());
		}
		return "success";
	}
	
	@Autowired
	private SpringClientFactory factory;
	
	@GetMapping(value = "/rule")
	@ResponseBody
	public void getMyDefinedRule() {
		ILoadBalancer balancer = factory.getLoadBalancer("default");
		System.out.println("Spring Cloud默认使用的均衡器："+balancer);
		
		@SuppressWarnings("unchecked")
		DynamicServerListLoadBalancer<Server> balancer2 = (DynamicServerListLoadBalancer<Server>) factory.getLoadBalancer("default");
		System.out.println("默认使用的规则："+balancer2.getRule().getClass().getName());
		
		@SuppressWarnings("unchecked")
		DynamicServerListLoadBalancer<Server> balancer3 = (DynamicServerListLoadBalancer<Server>) factory.getLoadBalancer("eureka-provider");
		System.out.println("eureka-provider使用的规则："+balancer3.getRule().getClass().getName());
	}

}