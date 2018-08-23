package netty2;

import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.Charset;

public class PlainOioServer {
	public static void main(String[] args) throws Exception {
		new PlainOioServer().server(8080);
	}
	public void server(int port) throws IOException{
		//将服务器绑定到指定端口
		final ServerSocket socket = new ServerSocket(port);
		try {
			for(;;){
				//接受连接
				final Socket clientsSocket = socket.accept();
				System.out.println("Accepted connection from " + clientsSocket);
				new Thread(new Runnable() {
					
					@Override
					public void run() {
						OutputStream out;
						try {
							out = clientsSocket.getOutputStream();
							out.write("Hi!!!\r\n".getBytes(Charset.forName("UTF-8")));
							out.flush();
							clientsSocket.close();
						} catch (Exception e) {
							e.printStackTrace();
						} finally{
							try {
								clientsSocket.close();
							} catch (Exception e2) {
								//ignore
							}
						}
					}
				}).start();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
