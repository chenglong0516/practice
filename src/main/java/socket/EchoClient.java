package socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class EchoClient
{
    public static void main(String[] args) throws IOException
    {
        Socket socket = null;
        PrintWriter pw = null;
        BufferedReader br = null;
        
        try
        {
            socket = new Socket("localhost", 1111);
            pw = new PrintWriter(socket.getOutputStream(), true);
            br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        }
        catch (UnknownHostException e)
        {
            System.err.println("Don't know abount host:localhost");
            System.exit(1);
        }
        System.out.println(br.readLine());
        System.out.println(br.readLine());
        BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
        String userInput;
        // 将客户端Socket输入流（即服务器端Socket的输出流）输出到标准输出上
        while ((userInput = stdIn.readLine()) != null)
        {
            pw.println(userInput);
            System.out.println(br.readLine());
        }
        // 同样的，将该关闭的资源给关闭掉
        pw.close();
        br.close();
        socket.close();
    }
}