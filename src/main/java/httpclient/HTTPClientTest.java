package httpclient;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.sf.json.JSONObject;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class HTTPClientTest {

	public static void main(String[] args) throws Exception{
//		test2();
		String result = httpPostWithJSON("http://back.chtfundtest.com/content/content/pageList.action");
        System.out.println(result);
	}

	/*
	 * 抓取博客园的首页
	 */
	private static void test1() throws IOException, ClientProtocolException {
		String targetUrl = "http://www.cnblogs.com/";
		
		//1 建立httpclient对象
		CloseableHttpClient client = HttpClients.createDefault();
		
		//2 建立Get请求
		HttpGet get = new HttpGet(targetUrl);
		
		//3 发送http Get请求
		CloseableHttpResponse res = client.execute(get);
		
		//4 处理结果
		if(res.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
			//相应体
			HttpEntity entity = res.getEntity();
			//拿到contentType
			ContentType contentType = ContentType.getOrDefault(entity);
			
			Charset charset = contentType.getCharset();
			String mimeType = contentType.getMimeType();
			
			//获取字节数组
			byte[] content = EntityUtils.toByteArray(entity);
			if(charset == null){
				//默认编码转字符串
				String temp = new String(content);
				String regEx = "(?=<meta).*?(?<=charset=[\\'|\\\"]?)([[a-z]|[A-Z]|[0-9]|-]*)";
		        Pattern p = Pattern.compile(regEx, Pattern.CASE_INSENSITIVE);
		        Matcher m = p.matcher(temp);
		        if (m.find() && m.groupCount() == 1) {
		          charset = Charset.forName(m.group(1));
		        } else {
		          charset = Charset.forName("ISO-8859-1");
		        }
			}
			System.out.println(new String(content, charset));
		}
	}
	
	/*
	 * 模拟知乎登陆的简单案例
	 */
	public static void test2(){
		String name = "16000000038";
	    String password = "aa1234";
	    
	    //全局请求设置
	    RequestConfig globalConfig = RequestConfig.custom().setCookieSpec(CookieSpecs.STANDARD).build();
	    //创建cookie store本地实例
	    CookieStore cookieStore = new BasicCookieStore();
	    //创建httpClient上下文
	    HttpClientContext context = HttpClientContext.create();
	    context.setCookieStore(cookieStore);
	    
	    //创建httpClent
	    CloseableHttpClient httpClient = HttpClients.custom().setDefaultRequestConfig(globalConfig).setDefaultCookieStore(cookieStore).build();
	    CloseableHttpResponse res = null;
	    
	    //创建本地http内容
	    try {
			try {
				 //创建get请求用了获取cookie
//				HttpGet get = new HttpGet("http://www.chtfundtest.com/");
				//创建post请求用了获取cookie
				HttpPost firstPost = new HttpPost("https://www.chtfundtest.com/apis/pc/content/findContentByCategory.action");
//		        json方式
//		        JSONObject jsonParam = new JSONObject();  
//		        StringEntity entity = new StringEntity(jsonParam.toString(),"utf-8");//解决中文乱码问题    
		        String aaa = "{\"hmac\":\"\",\"params\":{\"category\":\"notice\",\"curPage\":\"1\",\"pageSize\":\"2\",\"groupType\":\"contentCategory\"}}";
		        StringEntity entity = new StringEntity(aaa,"utf-8");//解决中文乱码问题    
		        entity.setContentEncoding("UTF-8");    
		        entity.setContentType("application/json");    
		        firstPost.setEntity(entity);

				res = httpClient.execute(firstPost);
				//获取常用cookie
				System.out.println("访问首页后的获取的常规Cookie:===============");
				for (Cookie c : cookieStore.getCookies()) {
					System.out.println(c.getName() + ": " + c.getValue());
				}
				res.close();
				
				// 构造post数据
		        List<NameValuePair> valuePairs = new LinkedList<NameValuePair>();
		        valuePairs.add(new BasicNameValuePair("custType", "1"));
		        valuePairs.add(new BasicNameValuePair("redirectUrl", "https://www.chtfundtest.com"));
		        valuePairs.add(new BasicNameValuePair("sourceType", "htjf"));
		        valuePairs.add(new BasicNameValuePair("username", name));
		        valuePairs.add(new BasicNameValuePair("password", password));
		        entity = new UrlEncodedFormEntity(valuePairs, Consts.UTF_8);
		        entity.setContentType("application/x-www-form-urlencoded");
		        
		        // 创建一个post请求
		        HttpPost post = new HttpPost("https://sso.chtwmtest.com//login.action");
		        // 注入请求数据
		        post.setEntity(entity);
		        res = httpClient.execute(post);
		        
		        // 打印响应信息，查看是否登陆是否成功
		        System.out.println("打印响应信息===========");
		        res.close();
		        
		        System.out.println("登陆成功后,新的Cookie:===============");
		        for (Cookie c : context.getCookieStore().getCookies()) {
		          System.out.println(c.getName() + ": " + c.getValue());
		        }
		        
		        // 构造一个新的get请求，用来测试登录是否成功
		        HttpGet newGet = new HttpGet("https://www.chtfundtest.com/apis/pc/account/recommand/recommandByLevel.action");
		        res = httpClient.execute(newGet, context);
		        String content = EntityUtils.toString(res.getEntity());
		        System.out.println("登陆成功后访问的页面===============");
		        System.out.println(content);
		        res.close();
			} finally {
				httpClient.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static String httpPostWithJSON(String url) throws Exception {

        HttpPost httpPost = new HttpPost(url);
        CloseableHttpClient client = null;
        String respContent = null;
        
	    //创建cookie store本地实例
	    CookieStore cookieStore = new BasicCookieStore();
	    //创建httpClient上下文
	    HttpClientContext context = HttpClientContext.create();
	    // 新建一个Cookie  
	    BasicClientCookie cookie = new BasicClientCookie("JSESSIONID", "539D8BE96E0F2191FA3B64A31D08FC67");  

	    cookieStore.addCookie(cookie);  
	    context.setCookieStore(cookieStore);
	    
//        json方式
        JSONObject jsonParam = new JSONObject();  
//        jsonParam.put("name", "admin");
//        jsonParam.put("pass", "123456");
        String aaa1 = "{\"hmac\":\"\",\"params\":{\"userCode\":\"HT123456\",\"password\":\"ht123456\",\"checkCode\":\"yy5y\"}}";
        String aaa = "{\"hmac\":\"\",\"params\":{\"category\":\"ruleOldAndNew\",\"title\":\"\",\"curPage\":1,\"pageSize\":20}}";
        StringEntity entity = new StringEntity(aaa,"utf-8");//解决中文乱码问题    
        entity.setContentEncoding("UTF-8");    
        entity.setContentType("application/json");    
        httpPost.setEntity(entity);
        System.out.println();
        
        //设置httpclient
        client = HttpClients.custom().setDefaultCookieStore(cookieStore).build();

//        表单方式
//        List<BasicNameValuePair> pairList = new ArrayList<BasicNameValuePair>(); 
//        pairList.add(new BasicNameValuePair("name", "admin"));
//        pairList.add(new BasicNameValuePair("pass", "123456"));
//        httpPost.setEntity(new UrlEncodedFormEntity(pairList, "utf-8"));   
        
        
        HttpResponse resp = client.execute(httpPost);
        if(resp.getStatusLine().getStatusCode() == 200) {
            HttpEntity he = resp.getEntity();
            respContent = EntityUtils.toString(he,"UTF-8");
        }
        return respContent;
    }
}


















