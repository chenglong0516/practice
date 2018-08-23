package httpclient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.http.Consts;
import org.apache.http.Header;
import org.apache.http.HeaderElement;
import org.apache.http.HeaderElementIterator;
import org.apache.http.HttpEntity;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.HttpVersion;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.conn.ConnectionKeepAliveStrategy;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultConnectionKeepAliveStrategy;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeaderElementIterator;
import org.apache.http.message.BasicHttpResponse;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;

//https://www.yeetrack.com/?p=779

public class Lesson1 {

	public static void main(String[] args) throws Exception {
//		demo0();
//		demo1();
//		demo2();
//		demo3();
//		demo4();
//		demo5();
//		demo6();
//		demo7();
//		demo8();
//		demo9();
		demo10();
	}

	private static void demo0() throws IOException, ClientProtocolException {
		CloseableHttpClient httpclient = HttpClients.createDefault();
	    HttpGet httpget = new HttpGet("https://www.yeetrack.com/");
	    CloseableHttpResponse response = httpclient.execute(httpget);
	    try {
	        HttpEntity entity = response.getEntity();
	        InputStream inputStream = entity.getContent();
	        printInputStream(inputStream);
	    } finally {
	        response.close();
	    }
	}

	private static void printInputStream(InputStream inputStream)
			throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
		String line = br.readLine();
		while(StringUtils.isNotBlank(line)){
			System.out.println(line);
			line = br.readLine();
		}
	}
	
	private static void demo1() throws Exception {
		URI uri = new URIBuilder()
	    .setScheme("http")
	    .setHost("www.google.com")
	    .setPath("/search")
	    .setParameter("q", "httpclient")
	    .setParameter("btnG", "Google Search")
	    .setParameter("aq", "f")
	    .setParameter("oq", "")
	    .build();
	    HttpGet httpget = new HttpGet(uri);
	    System.out.println(httpget.getURI());
	}
	
	private static void demo2() throws Exception {
		HttpResponse response = new BasicHttpResponse(HttpVersion.HTTP_1_1, HttpStatus.SC_OK, "OK");

	    System.out.println(response.getProtocolVersion());
	    System.out.println(response.getStatusLine().getStatusCode());
	    System.out.println(response.getStatusLine().getReasonPhrase());
	    System.out.println(response.getStatusLine().toString());
	}
	
	private static void demo3() throws Exception {
		HttpResponse response = new BasicHttpResponse(HttpVersion.HTTP_1_1, HttpStatus.SC_OK, "OK");
	    response.addHeader("Set-Cookie", "c1=a; path=/; domain=yeetrack.com");
	    response.addHeader("Set-Cookie", "c2=b; path=\"/\", c3=c; domain=\"yeetrack.com\"");
//	    Header h1 = response.getFirstHeader("Set-Cookie");
//	    System.out.println(h1);
//	    Header h2 = response.getLastHeader("Set-Cookie");
//	    System.out.println(h2);
//	    Header[] hs = response.getHeaders("Set-Cookie");
//	    System.out.println(hs.length);
	    
	    //迭代器
//	    HeaderIterator it = response.headerIterator("Set-Cookie");
//	    while (it.hasNext()) { System.out.println(it.next()); } 
	    
	    HeaderElementIterator it = new BasicHeaderElementIterator(response.headerIterator("Set-Cookie"));

	    while (it.hasNext()) {
	        HeaderElement elem = it.nextElement(); 
	        System.out.println(elem.getName() + " = " + elem.getValue());
	        NameValuePair[] params = elem.getParameters();
	        for (int i = 0; i < params.length; i++) {
	            System.out.println(" " + params[i]);
	        }
	        System.out.println();
	    }
	}
	
	//使用Http实体
	private static void demo4() throws Exception{
		StringEntity myEntity = new StringEntity("important message", ContentType.create("text/plain", "UTF-8"));
		System.out.println(myEntity.getContentType());
		System.out.println(myEntity.getContentLength());
		System.out.println(EntityUtils.toString(myEntity));
		System.out.println(EntityUtils.toByteArray(myEntity).length);
	}
	
	//确保底层资源链接被释放
	private static void demo5() throws Exception{
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet("https://www.yeetrack.com/");
		CloseableHttpResponse response = httpClient.execute(httpGet);
		try {
			HttpEntity entity = response.getEntity();
			if(null!=entity){
				InputStream inputStream = entity.getContent();
				try {
					printInputStream(inputStream);
				} finally {
					inputStream.close();
				}
			}
		} catch (Exception e) {
			response.close();
		}
	}
	
	//消耗HTTP实体内容
	private static void demo6() throws Exception{
		CloseableHttpClient httpclient = HttpClients.createDefault();
	    HttpGet httpget = new HttpGet("https://www.yeetrack.com/");
	    CloseableHttpResponse response = httpclient.execute(httpget);
	    try {
	        HttpEntity entity = response.getEntity();
	        if (entity != null) {
	            long len = entity.getContentLength();
	            System.out.println("entity.getContentLength() :" + String.valueOf(len));
	            if (len != -1 && len < 2048) {
	                System.out.println(EntityUtils.toString(entity));
	            } else {
	                // Stream content out
	            }
	        }
	    } finally {
	        response.close();
	    }
	}
	
	//HTML表单
	private static void demo7() throws Exception{
		CloseableHttpClient httpclient = HttpClients.createDefault();
		List<NameValuePair> formparams = new ArrayList<NameValuePair>();
	    formparams.add(new BasicNameValuePair("param1", "value1"));
	    formparams.add(new BasicNameValuePair("param2", "value2"));
	    UrlEncodedFormEntity entity = new UrlEncodedFormEntity(formparams, Consts.UTF_8);
	    HttpPost httppost = new HttpPost("https://www.yeetrack.com/handler.do");
	    httppost.setEntity(entity);
	    System.out.println(EntityUtils.toString(httppost.getEntity()));
	    CloseableHttpResponse response = httpclient.execute(httppost);
	    try {
	        HttpEntity entity1 = response.getEntity();
	        if (entity1 != null) {
	            long len = entity1.getContentLength();
	            System.out.println("entity.getContentLength() :" + String.valueOf(len));
	            if (len != -1 && len < 2048) {
	                System.out.println(EntityUtils.toString(entity1));
	            } else {
	                // Stream content out
	            }
	        }
	    } finally {
	        response.close();
	    }
	}
	
	//最简单也是最方便的处理http响应的方法
	private static void demo8() throws Exception{
//		CloseableHttpClient httpClient = HttpClients.createDefault();
		CloseableHttpClient httpClient = demo9();
		
		HttpGet httpget = new HttpGet("http://172.16.162.10:7001/openapi/fundrestful/query/dcdatadictionaryquery?key_no=1013&sys_name=TA");
		
		ResponseHandler<Object> rh = new ResponseHandler<Object>() {

			@Override
			public Object handleResponse(HttpResponse response)
					throws ClientProtocolException, IOException {
				StatusLine statusLine = response.getStatusLine();
	            HttpEntity entity = response.getEntity();
	            if (statusLine.getStatusCode() >= 300) {
	                throw new HttpResponseException(
	                        statusLine.getStatusCode(),
	                        statusLine.getReasonPhrase());
	            }
	            if (entity == null) {
	                throw new ClientProtocolException("Response contains no content");
	            }
//	            printInputStream(entity.getContent());
	            ContentType contentType = ContentType.getOrDefault(entity);
	            Charset charset = contentType.getCharset();
	            String jsonStr = EntityUtils.toString(entity, charset);
	            JSONObject jsonObject = JSONObject.fromObject(jsonStr);
	            System.out.println(jsonObject.get("message"));
				return null;
			}
		};
		httpClient.execute(httpget, rh);
	}
	
	//HttpClient接口 自定义
	private static CloseableHttpClient demo9(){
		ConnectionKeepAliveStrategy keepAliveStrat = new DefaultConnectionKeepAliveStrategy() {

	        @Override
	        public long getKeepAliveDuration(
	            HttpResponse response,
	            HttpContext context) {
	                long keepAlive = super.getKeepAliveDuration(response, context);
	                System.out.println("keepAlive is " + keepAlive);
	                if (keepAlive == -1) {
	                    //如果服务器没有设置keep-alive这个参数，我们就把它设置成5秒
	                    keepAlive = 5000;
	                }
	                return keepAlive;
	        }

	    };
	    //定制我们自己的httpclient
	    CloseableHttpClient httpclient = HttpClients.custom()
	            .setKeepAliveStrategy(keepAliveStrat)
	            .build();
	    return httpclient;
	}
	
	//HttpContext上下文, 同一个逻辑会话中，HttpContext中会保持状态
	private static void demo10() throws Exception{
		CloseableHttpClient httpclient = HttpClients.createDefault();
	    RequestConfig requestConfig = RequestConfig.custom()
	            .setSocketTimeout(1000)
	            .setConnectTimeout(1000)
	            .build();

	    HttpGet httpget1 = new HttpGet("http://172.16.162.10:7001/openapi/fundrestful/query/dcdatadictionaryquery?key_no=1013&sys_name=TA");
	    httpget1.setConfig(requestConfig);
	    HttpContext context = new HttpClientContext();
		CloseableHttpResponse response1 = httpclient.execute(httpget1, context);
	    try {
	        HttpEntity entity1 = response1.getEntity();
	    } finally {
	        response1.close();
	    }
	    //httpget2被执行时，也会使用httpget1的上下文
	    HttpGet httpget2 = new HttpGet("http://172.16.162.10:7001/openapi/fundrestful/query/dcdatadictionaryquery?key_no=1013&sys_name=TB");
	    CloseableHttpResponse response2 = httpclient.execute(httpget2, context);
	    try {
	        HttpEntity entity2 = response2.getEntity();
	    } finally {
	        response2.close();
	    }
	    HttpClientContext clientContext = HttpClientContext.adapt(context);
	    HttpRequest request = clientContext.getRequest();
	    Header[] headers = request.getHeaders("Accept");
	    for (Header header : headers) {
	    	System.out.println(header.getName() + " === " + header.getValue());
		}
	}
}
