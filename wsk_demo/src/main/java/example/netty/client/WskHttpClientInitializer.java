package example.netty.client;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpClientCodec;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

/**
 * @Description 初始化业务责任链，系统定义的 及 自定义的业务处理器
 * @Author acer
 * @Date 2022/3/3 10:39
 * @Version 1.0
 */
public class WskHttpClientInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline channelPipeline = socketChannel.pipeline();
        channelPipeline.addLast(new StringDecoder());
        channelPipeline.addLast(new StringEncoder());
        channelPipeline.addLast(new WskHttpClientHandler());
//        channelPipeline.addLast(new WskHttpClientAdapter());
    }

}
