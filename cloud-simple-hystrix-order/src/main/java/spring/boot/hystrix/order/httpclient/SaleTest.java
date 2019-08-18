package spring.boot.hystrix.order.httpclient;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class SaleTest {
	public static void main(String[] args) {
		String url = "http://localhost:8080/normal";
		//1.创建一个默认的http客户端  
        CloseableHttpClient httpClient = HttpClients.createDefault();
        //2.创建一个GET请求
        HttpGet httpGet = new HttpGet(url);
        try {
        	//3.获取响应
			HttpResponse httpResponse = httpClient.execute(httpGet);
			System.out.println(EntityUtils.toString(httpResponse.getEntity()));
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}  
	}
}
