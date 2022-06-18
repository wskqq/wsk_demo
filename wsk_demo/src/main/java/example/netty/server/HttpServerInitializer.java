package example.netty.server;

import example.netty.server.HttpServerChannelAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.*;

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
//        ChannelPipeline channelPipeline = socketChannel.pipeline();
//        // 处理http消息的编解码，该行配置等同于下面两句的配置
//        channelPipeline.addLast(new HttpServerCodec());
////        channelPipeline.addLast("httpResponseEndcoder",new HttpResponseEncoder());
////        channelPipeline.addLast("httpRequestDecoder",new HttpRequestDecoder());
//        // 添加自定义的ChannelHandler
//        channelPipeline.addLast(new HttpServerChannelAdapter());
////        channelPipeline.addLast(new HttpServerChannelHandler());

        ChannelPipeline pipeline = socketChannel.pipeline();

        //http请求编解码器,请求解码，响应编码
        pipeline.addLast("serverCodec", new HttpServerCodec());
        //http请求报文聚合为完整报文，最大请求报文为10M
        pipeline.addLast("aggregator", new HttpObjectAggregator(10 * 1024 * 1024));
        //响应报文压缩
        pipeline.addLast("compress", new HttpContentCompressor());
        //业务处理handler
//        pipeline.addLast( new HttpServerChannelAdapter());
        pipeline.addLast(new HttpServerChannelHandler());
    }
}
