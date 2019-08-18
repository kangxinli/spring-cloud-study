package spring.boot.feign.consumer.contract;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)			//只能修饰方法的注解
@Retention(RetentionPolicy.RUNTIME)	//运行时生效
public @interface MyRequest {
 
	String url();		//http请求的路径
	String method();	//请求的方式
	
}