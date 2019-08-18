package spring.boot.hystrix.order.hystrix;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;

/**
 * 用Hystrix的方式来执行请求
 *
 */
public class SaleCommand extends HystrixCommand<String> {
	 
	public SaleCommand(){
		super(HystrixCommandGroupKey.Factory.asKey("myGroup"));
	}
	
	protected String run() throws Exception {
		String url = "http://localhost:8080/normal";
		//1.创建一个默认的http客户端  
        CloseableHttpClient httpClient = HttpClients.createDefault();
        //2.创建一个GET请求
        HttpGet httpGet = new HttpGet(url);
        //3.获取响应
		HttpResponse httpResponse = httpClient.execute(httpGet);
		return EntityUtils.toString(httpResponse.getEntity());
	}
	
}