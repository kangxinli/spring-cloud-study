package spring.boot.hystrix.order.hystrix;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;

public class ErrorCommand extends HystrixCommand<String>{
	
	public ErrorCommand(){
		super(HystrixCommandGroupKey.Factory.asKey("myGroup"));
	}
	
	protected String run() throws Exception {
		String url = "http://localhost:8080/trouble";
		//1.创建一个默认的http客户端  
        CloseableHttpClient httpClient = HttpClients.createDefault();
        //2.创建一个GET请求
        HttpGet httpGet = new HttpGet(url);
        //3.获取响应
		HttpResponse httpResponse = httpClient.execute(httpGet);
		return EntityUtils.toString(httpResponse.getEntity());
	}
	
	/**
	 * 抛出异常的原因Hystrix默认的超时时间是1秒，请求超时，就进入了Hystrix的回退逻辑，但是我们又没有编写回退逻辑，才造成了错误。
	 * 
	 * 这里可以为ErrorCommand增加回退逻辑，做起来也很简单，只要去实现getFallback()方法就OK了
	 */
	@Override
	protected String getFallback() {
		System.out.println("这里是回退逻辑");
		return "fail";
	}
}
