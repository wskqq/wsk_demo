package example.netty;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;
import io.netty.handler.codec.http.HttpServerCodec;

/**
 * 业务责任链
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
//        channelPipeline.addLast(new HttpServerChannelHandler());
        channelPipeline.addLast(new HttpServerChannelAdapter());
    }
}
