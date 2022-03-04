package example.netty.client;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpClientCodec;
import io.netty.handler.codec.http.HttpObjectAggregator;

/**
 * 初始化业务责任链，系统定义的 及 自定义的业务处理类
 * 发送请求拦截类
 */
/**
 * @Description TODO
 * @Author acer
 * @Date 2022/3/3 10:39
 * @Version 1.0
 */
public class WskHttpClientInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline channelPipeline = socketChannel.pipeline();
        channelPipeline.addLast(new HttpClientCodec());
        // 当我们用POST方式请求服务器的时候，对应的参数信息是保存在message body中的,如果只是单纯的
        // 用HttpServerCodec是无法完全的解析Http POST请求的，因为HttpServerCodec只能获取uri中参数，
        // 所以需要加上HttpObjectAggregator。
        channelPipeline.addLast(new HttpObjectAggregator(65536));
        channelPipeline.addLast(new WskHttpClientHandler());
    }
}
