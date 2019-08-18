package spring.boot.eureka.consumer.myribbon;

import org.springframework.cloud.netflix.ribbon.RibbonClient;

/**
 * 自定--负载均衡器
 * @author Administrator
 *
 */
@RibbonClient(name = "eureka-provider", configuration = MyConfig.class)
public class MyLoadBalanceClient {

}
