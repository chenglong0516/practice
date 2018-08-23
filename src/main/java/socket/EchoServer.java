package socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {

	public static void main(String[] args) throws IOException {
		ServerSocket ss = null;
		PrintWriter pw = null;
		BufferedReader br = null;
		
		try {
			//实例化监听接口
			ss = new ServerSocket(1111);
		} catch (Exception e) {
			System.err.println("Could not listen on port:1111");
			System.exit(1);
		}
		
		Socket incoming = null;
		try {
			while(true){
				incoming = ss.accept();
				pw = new PrintWriter(incoming.getOutputStream(), true);
				// 先将字节流通过InputStreamReader转换为字符流，之后将字符流放入缓冲之中
				br = new BufferedReader(new InputStreamReader(incoming.getInputStream()));
				pw.println("Hello!...");
				pw.println("Enter BYE to exit");
				pw.flush(); 
				while(true){
					String str = br.readLine();
					if(str == null){
						break;
					}else{
						pw.println("Echo:" + str);
						pw.flush();
						if ("BYE".equalsIgnoreCase(str.trim()))
						{
							break;
						}
					}
				}
			}
		} finally{
			// 该close的资源都close掉
			pw.close();
			br.close();
			incoming.close();
			ss.close();
		}
	}
}
