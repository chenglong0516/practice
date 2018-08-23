package socket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class HelloServer {
	public static void main(String[] args) throws IOException {
		ServerSocket serverSocket = null;
		try {
			serverSocket = new ServerSocket(9999);
		}
        catch (IOException e)
        {
            System.err.print("Could not listen on port:9999");
            System.exit(1);
        }
        Socket clientSocket = null;
        try {
			clientSocket = serverSocket.accept();
		} catch (Exception e) {
            System.err.println("Accept failed");
            System.exit(1);
		}
        // 客户端有数据了就向屏幕打印Hello World
        System.out.print("Hello World");
        clientSocket.close();
        serverSocket.close();
	}
}
