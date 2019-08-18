package spring.boot.eureka.consumer.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CacheController {

	@Autowired
	CacheService cacheService;

	@RequestMapping(value = "/myCache/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public String router(@PathVariable Integer id) {
		for (int i = 0; i < 4; i++) {
			System.out.println("这是第" + (i + 1) + "次调用");
			cacheService.getCachePerson(id);
		}
		return "success";
	}

	@RequestMapping(value = "/clearCache/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public String clearCache(@PathVariable Integer id) {
		for (int i = 0; i < 4; i++) {
			cacheService.getCache(id);
		}
		System.out.println("<<下面执行清除缓存>>");
		cacheService.clearCache(id);
		for (int i = 0; i < 4; i++) {
			cacheService.getCache(id);
		}
		return "success";
	}
}