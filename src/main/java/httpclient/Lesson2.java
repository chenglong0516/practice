package httpclient;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import org.apache.http.HttpClientConnection;
import org.apache.http.HttpHost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.conn.ConnectionPoolTimeoutException;
import org.apache.http.conn.ConnectionRequest;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.impl.conn.BasicHttpClientConnectionManager;

/*
 * 连接管理
 * 持久链接，减小频繁建立连接的开销
 */

public class Lesson2 {

	public static void main(String[] args) throws Exception {
		test1();
	}
	
	/**
	 * 从连接管理器中取得一个http连接
	 * @throws ExecutionException 
	 * @throws InterruptedException 
	 * @throws ConnectionPoolTimeoutException 
	 */
	private static void test1() throws Exception {
		HttpClientContext context = HttpClientContext.create();
		HttpClientConnectionManager connMrg = new BasicHttpClientConnectionManager();
		HttpRoute route = new HttpRoute(new HttpHost("www.yeetrack.com", 80));
		
		//获取新连接，可能耗费时间
		ConnectionRequest connRequest = connMrg.requestConnection(route, null);
		//10秒超时
		HttpClientConnection conn = connRequest.get(10, TimeUnit.SECONDS);
		try {
			//如果创建连接失败
			if(!conn.isOpen()){
				connMrg.connect(conn, route, 1000, context);
				connMrg.routeComplete(conn, route, context);
			}
			//进行自己操作
		} catch (Exception e) {
			connMrg.releaseConnection(conn, null, 1, TimeUnit.MINUTES);
		}
	}
}
