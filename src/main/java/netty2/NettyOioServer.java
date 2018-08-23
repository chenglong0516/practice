package netty2;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.oio.OioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.oio.OioServerSocketChannel;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;

public class NettyOioServer {
	
	public static void main(String[] args) throws Exception {
		new NettyOioServer().server(8080);
	}
	
	/**
	 * @param port
	 * @throws Exception
	 */
	public void server(int port) throws Exception {
		final ByteBuf buf = Unpooled.unreleasableBuffer(Unpooled.copiedBuffer(
				"Hi Netty!\r\n", Charset.forName("UTF-8")));
//		EventLoopGroup group = new OioEventLoopGroup();
		EventLoopGroup group = new NioEventLoopGroup();
		try {
			ServerBootstrap b = new ServerBootstrap();
			b.group(group)
				.channel(NioServerSocketChannel.class)
//				.channel(OioServerSocketChannel.class)
				.localAddress(new InetSocketAddress(port))
				.childHandler(new ChannelInitializer<SocketChannel>() {
					@Override
					protected void initChannel(SocketChannel ch)
							throws Exception {
						ch.pipeline().addLast(new ChannelInboundHandlerAdapter(){

							@Override
							public void channelActive(ChannelHandlerContext ctx)
									throws Exception {
								ctx.writeAndFlush(buf.duplicate()).addListener(ChannelFutureListener.CLOSE);
							}
							
						});
					}
				});
			//綁定服務器以接受连接
			ChannelFuture future = b.bind().sync();
			future.channel().closeFuture().sync();
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			group.shutdownGracefully().sync();
		}
	}
}
