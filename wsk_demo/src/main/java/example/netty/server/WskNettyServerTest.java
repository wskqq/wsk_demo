package example.netty.server;

import example.netty.server.HttpServerInitializer;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * Netty实现Http服务端实现测试类
 * 服务端启动类
 * 测试访问地址：  http://ip:port/
 */
/**
 * @Description TODO
 * @Author acer
 * @Date 2022/3/3 10:39
 * @Version 1.0
 */
public class WskNettyServerTest {
    public static void main(String[] args) {
        // 构建两个线程组
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        // 启动辅助类
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap.group(bossGroup,workerGroup)
                .channel(NioServerSocketChannel.class)
                .childHandler(new HttpServerInitializer())
                .option(ChannelOption.SO_BACKLOG,1024)
                .childOption(ChannelOption.SO_KEEPALIVE, true);

        try {
            ChannelFuture channelFuture = serverBootstrap.bind(8888).sync();
            System.out.println("服务器启动成功");
            // 等待服务端口关闭
            channelFuture.channel().closeFuture().sync();
            System.out.println("服务器执行结束");
        } catch (InterruptedException e) {
            System.out.println("绑定端口报错");
        }finally {
            // 优雅退出，释放线程池资源
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }

}
