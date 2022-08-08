package example.netty.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;

/**
 * @Description Netty客户端启动类
 * @Author acer
 * @Date 2022/3/3 10:39
 * @Version 1.0
 */
public class WskNettyClientTest {
    public static void main(String[] args) {
        String ip = "127.0.0.1";
        int port = 8889;
        EventLoopGroup eventLoopGroup = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(eventLoopGroup)
                .channel(NioSocketChannel.class)
                .handler(new WskHttpClientInitializer());

        try {
             // 等待服务端口关闭
            ChannelFuture f = bootstrap.connect(ip,port).sync();

            f.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            System.out.println(e);
        }finally {
            eventLoopGroup.shutdownGracefully();
        }

    }
}
