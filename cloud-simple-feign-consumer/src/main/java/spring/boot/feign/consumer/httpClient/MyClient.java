package spring.boot.feign.consumer.httpClient;

import java.io.IOException;
import java.net.URI;
import java.util.Collection;
import java.util.HashMap;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import feign.Client;
import feign.Request;
import feign.Request.Options;
import feign.Response;

public class MyClient implements Client {
	 
	@Override
	public Response execute(Request request, Options options) throws IOException {
		System.out.println("这里是自定义的Feign客户端");
		try {
			//1.创建一个默认的http客户端
			CloseableHttpClient httpClient = HttpClients.createDefault();
			//2.获取请求中使用的http方法
			final String method = request.method();
			//3.创建一个httpClient的HttpRequest
			HttpRequestBase httpRequest = new HttpRequestBase() {
				@Override
				public String getMethod() {
					return method;
				}
			};
			//4.设置httpClient的请求地址
			httpRequest.setURI(new URI(request.url()));
			//5.执行请求，得到响应
			HttpResponse httpResponse = httpClient.execute(httpRequest);
			//6.解析请求响应体
			byte[] body = EntityUtils.toByteArray(httpResponse.getEntity());
			//7.将httpClient的响应体转化为feign的Response
			Response response = Response.builder().body(body)
					.headers(new HashMap<String, Collection<String>>())
					.status(httpResponse.getStatusLine().getStatusCode())
					.build();
			return response;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
 
}