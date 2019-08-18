package spring.boot.eureka.consumer.cache;

import org.springframework.stereotype.Service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheRemove;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheResult;

import spring.boot.eureka.consumer.hystrix.Person;

@Service
public class CacheService {
 
	@CacheResult
	@HystrixCommand
	public Person getCachePerson(Integer id){
		System.out.println("这里是缓存服务");
		return null;
	};
	
	@CacheResult
	@HystrixCommand(commandKey = "myCacheKey")
	public String getCache(Integer id){
		System.out.println("===>获取缓存数据");
		return null;
	}
	 
	@CacheRemove(commandKey = "myCacheKey")
	@HystrixCommand
	public String clearCache(Integer id){
		System.out.println("===>清除缓存");
		return null;
	}
}