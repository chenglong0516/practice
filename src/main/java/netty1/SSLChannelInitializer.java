package netty1;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;

public class SSLChannelInitializer extends ChannelInitializer<SocketChannel> {

	@Override
	protected void initChannel(SocketChannel arg0) throws Exception {
		String keyStoreFilePath = "/root/.ssl/test.pkcs12";
        String keyStorePassword = "Password@123";
		
	}

}
