package spring.boot.feign.consumer.contract;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import feign.Contract.BaseContract;
import feign.MethodMetadata;

public class MyContract extends BaseContract {
	 
	@Override
	protected void processAnnotationOnClass(MethodMetadata data, Class<?> clz) {
		//基于类的注解
	}
 
	@Override
	protected void processAnnotationOnMethod(MethodMetadata data,
			Annotation anotation, Method method) {
		//基于方法的注解
		if(MyRequest.class.isInstance(anotation)){
			MyRequest myRequest = method.getAnnotation(MyRequest.class);
			String url = myRequest.url();
			String httpMethod = myRequest.method();
			data.template().append(url);//我们传入的是/hello，需要拼接到url后面
			data.template().method(httpMethod);
		}
	}
 
	@Override
	protected boolean processAnnotationsOnParameter(MethodMetadata arg0,
			Annotation[] arg1, int arg2) {
		//基于参数的注解
		return false;
	}
 
}