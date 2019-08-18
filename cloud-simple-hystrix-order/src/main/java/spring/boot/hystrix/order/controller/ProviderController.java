package spring.boot.hystrix.order.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProviderController {

	@RequestMapping(value = "/normal", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public String normalHello() {
		return "success";
	}

	@RequestMapping(value = "/trouble", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public String errorHello() {
		try {
			// 模拟数据库故障，不能及时返回数据
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return "success";
	}
}