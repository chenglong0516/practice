package netty2;

import java.net.InetSocketAddress;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class EchoServer {
	private final int port;
	
	public EchoServer(int port){
		this.port = port;
	}
	
	public static void main(String[] args)throws Exception{
//		if(args.length != 1){
//			System.err.println("Usage:" + HttpServer.class.getSimpleName() + " <port>");
//			return;
//		}
//		int port = Integer.parseInt(args[0]);
		new EchoServer(8080).start();
	}
	
	public void start()throws Exception{
		final EchoServerHandler serverHandler = new EchoServerHandler();
		//用户接受和处理新连接
		EventLoopGroup group = new NioEventLoopGroup();
		try {
			ServerBootstrap b = new ServerBootstrap();
			b.group(group)
				//指定Channel类型
				.channel(NioServerSocketChannel.class)
				//设置本地地址
				.localAddress(new InetSocketAddress(port))
				.childHandler(new ChannelInitializer<SocketChannel>() {
					/*
					一个新连接被接受时创建了一个新的Channel，ChannelInitializer将
					serverHandler添加到这个Channel的ChannelPipeLine中
					*/
					@Override
					protected void initChannel(SocketChannel ch)
							throws Exception {
						ch.pipeline().addLast(serverHandler);
					}
				});
			//绑定服务器（等待绑定完成）
			ChannelFuture f = b.bind().sync();
			//程序阻塞知道服务器的Channel关闭
			f.channel().closeFuture().sync();
		} catch (Exception e) {
			// TODO: handle exception
		} finally{
			group.shutdownGracefully().sync();
		}
	}
}
