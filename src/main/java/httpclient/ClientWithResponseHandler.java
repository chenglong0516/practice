package httpclient;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.util.EntityUtils;

/*
请求步骤：
创建CloseableHttpClient对象。
创建请求方法的实例，并指定请求URL。如果需要发送GET请求，创建HttpGet对象；如果需要发送POST请求，创建HttpPost对象。
如果需要发送请求参数，可可调用setEntity(HttpEntity entity)方法来设置请求参数。setParams方法已过时（4.4.1版本）。
调用HttpGet、HttpPost对象的setHeader(String name, String value)方法设置header信息，或者调用setHeaders(Header[] headers)设置一组header信息。
调用CloseableHttpClient对象的execute(HttpUriRequest request)发送请求，该方法返回一个CloseableHttpResponse。
调用HttpResponse的getEntity()方法可获取HttpEntity对象，该对象包装了服务器的响应内容。程序可通过该对象获取服务器的响应内容；调用CloseableHttpResponse的getAllHeaders()、getHeaders(String name)等方法可获取服务器的响应头。
释放连接。无论执行方法是否成功，都必须释放连接
 * 
 */
public class ClientWithResponseHandler{
	public final static void main(String[] args) throws Exception {
//        test1();
        test2();
    }

	private static void test2() throws Exception, IOException {
		String body = "";

        //采用绕过验证的方式处理https请求  
        SSLContext sslcontext = createIgnoreVerifySSL();  

        //设置协议http和https对应的处理socket链接工厂的对象  
        Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create()  
            .register("http", PlainConnectionSocketFactory.INSTANCE)  
            .register("https", new SSLConnectionSocketFactory(sslcontext))  
            .build();  
        PoolingHttpClientConnectionManager connManager = new PoolingHttpClientConnectionManager(socketFactoryRegistry);  

        CloseableHttpClient client = null;
        try{
            //创建get方式请求对象  
            HttpPost post = new HttpPost("https://www.chtwmtest.com/apis/common/validate/getValidateCode.action");

            //指定报文头Content-type、User-Agent  
            post.setHeader("Content-type", "application/json;charset=UTF-8");  
            post.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; rv:6.0.2) Gecko/20100101 Firefox/6.0.2");
            StringEntity entity = new StringEntity("{}","utf-8");//解决中文乱码问题    
            entity.setContentEncoding("UTF-8");    
            entity.setContentType("application/json");    
            post.setEntity(entity);
            System.out.println();
            
    	    // 新建一个Cookie  
    	    BasicClientCookie cookie = new BasicClientCookie("PEFJSESSIONID", "5a18bf54-b703-4284-bc73-a4a5e945e76c");  
    	    cookie.setDomain("www.chtwmtest.com");
    	    CookieStore cookieStore = new BasicCookieStore();
    	    //创建httpClient上下文
    	    HttpClientContext context = HttpClientContext.create();
    	    cookieStore.addCookie(cookie);  
    	    context.setCookieStore(cookieStore);
            
            //创建自定义的httpclient对象  
            client = HttpClients.custom().setConnectionManager(connManager).setDefaultCookieStore(cookieStore).build();  

            //执行请求操作，并拿到结果（同步阻塞）  
            CloseableHttpResponse response = client.execute(post, context);  

            //获取结果实体  
            HttpEntity entity1 = response.getEntity(); 
            if (entity1 != null) {  
                //按指定编码转换结果实体为String类型  
                body = EntityUtils.toString(entity1, "utf8");  
            }  

            EntityUtils.consume(entity1);  
            //释放链接  
            response.close(); 
            System.out.println("body:" + body);
        } finally{
            client.close();
       }
		
	}

	private static void test1() throws IOException, ClientProtocolException {
		CloseableHttpClient httpclient = HttpClients.createDefault();
        try {
            HttpGet httpget = new HttpGet("https://www.chtwmtest.com/apis/common/validate/getValidateCode.action/");

            System.out.println("Executing request " + httpget.getRequestLine());

            // Create a custom response handler
            ResponseHandler<String> responseHandler = new ResponseHandler<String>() {

                @Override
                public String handleResponse(
                        final HttpResponse response) throws ClientProtocolException, IOException {
                    int status = response.getStatusLine().getStatusCode();
                    if (status >= 200 && status < 300) {
                        HttpEntity entity = response.getEntity();
                        return entity != null ? EntityUtils.toString(entity, "utf8") : null;
                    } else {
                        throw new ClientProtocolException("Unexpected response status: " + status);
                    }
                }

            };
            String responseBody = httpclient.execute(httpget, responseHandler);
            System.out.println("----------------------------------------");
            System.out.println(responseBody);
        } finally {
            httpclient.close();
        }
	}
	
	/** 
	* 绕过验证 
	*   
	* @return 
	* @throws NoSuchAlgorithmException  
	* @throws KeyManagementException  
	*/  
	public static SSLContext createIgnoreVerifySSL() throws NoSuchAlgorithmException, KeyManagementException {  
	        SSLContext sc = SSLContext.getInstance("SSLv3");  

	        // 实现一个X509TrustManager接口，用于绕过验证，不用修改里面的方法  
	        X509TrustManager trustManager = new X509TrustManager() {
				
				@Override
				public X509Certificate[] getAcceptedIssuers() {
					// TODO Auto-generated method stub
					return null;
				}
				
				@Override
				public void checkServerTrusted(X509Certificate[] chain, String authType)
						throws CertificateException {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void checkClientTrusted(X509Certificate[] chain, String authType)
						throws CertificateException {
					// TODO Auto-generated method stub
					
				}
			};

	        sc.init(null, new TrustManager[] { trustManager }, null);  
	        return sc;  
	    }
}
