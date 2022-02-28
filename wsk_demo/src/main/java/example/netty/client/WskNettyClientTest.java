package example.netty.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * Netty客户端启动类
 */
public class WskNettyClientTest {
    public static void main(String[] args) {
        String ip = "127.0.0.1";
        int port = 8888;
        EventLoopGroup eventLoopGroup = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(eventLoopGroup)
                .channel(NioSocketChannel.class)
                .handler(new WskHttpClientInitializer());

        try {
            // 启动客户端
            ChannelFuture channelFuture = bootstrap.connect(ip,port).sync();
            // 等待服务端口关闭
            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            System.out.println(e);
        }finally {
            eventLoopGroup.shutdownGracefully();
        }

    }
}
