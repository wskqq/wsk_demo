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
 * Netty客户端启动类
 */
/**
 * @Description TODO 能运行发送不通交易
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
//            Channel channel = bootstrap.connect(ip,port).sync().channel();
//            while (true) {
//                //向服务端发送内容
//                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
//                String content = reader.readLine();
//                if (StringUtils.isNotEmpty(content)) {
//                    if (StringUtils.equalsIgnoreCase(content, "q")) {
//                        System.exit(1);
//                    }
//                    channel.writeAndFlush(content);
//                }
//            }

            ChannelFuture channel = bootstrap.connect(ip,port)
                    .addListener(new ChannelFutureListener() {
                        @Override
                        public void operationComplete(ChannelFuture channelFuture) throws Exception {
                            if(channelFuture.isSuccess()){
                                channelFuture.channel().write(Unpooled.buffer().writeBytes("123".getBytes()));
                                channelFuture.channel().flush();
                            }
                        }
                    });
            // 等待服务端口关闭
            channel.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            System.out.println(e);
            System.out.println("11111");
        }finally {
            eventLoopGroup.shutdownGracefully();
        }

    }
}
