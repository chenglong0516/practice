package netty2;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.util.CharsetUtil;

@Sharable
public class EchoClientHandler extends SimpleChannelInboundHandler<ByteBuf> {

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
			throws Exception {
		//记录错误并关闭Channel
		cause.printStackTrace();
		ctx.close();
	}

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		//当被通知的channel是激活的时候，发送一条信息
		ctx.writeAndFlush(Unpooled.copiedBuffer("Netty rocks!!!", CharsetUtil.UTF_8));
	}

	//原来的channelRead0
	@Override
	protected void messageReceived(ChannelHandlerContext ctx, ByteBuf msg)
			throws Exception {
		//记录已接收消息的转储
		System.out.println("Client received： " + msg.toString(CharsetUtil.UTF_8));
	}

}
