package example.netty.server;

import example.netty.server.HttpServerChannelAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;

/**
 * 初始化业务责任链，系统定义的 及 自定义的业务处理类
 * 接收请求拦截类
 */
/**
 * @Description TODO
 * @Author acer
 * @Date 2022/3/3 10:39
 * @Version 1.0
 */
public class HttpServerInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline channelPipeline = socketChannel.pipeline();
        // 处理http消息的编解码，该行配置等同于下面两句的配置
//        channelPipeline.addLast(new HttpServerCodec());
        channelPipeline.addLast("httpResponseEndcoder",new HttpResponseEncoder());
        channelPipeline.addLast("httpRequestDecoder",new HttpRequestDecoder());
        // 添加自定义的ChannelHandler
        channelPipeline.addLast(new HttpServerChannelAdapter());
    }
}
