package example.netty.server;

import example.netty.server.HttpServerChannelAdapter;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.*;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

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

    /**
     * ChannelInboundHandlerAdapter 实现类执行顺序与添加顺序一致
     * ChannelOutboundHandlerAdapter 实现类执行顺序与添加顺序相反
     * pipeline.addLast 从tail端添加处理器
     * pipeline.addFirst 从head端添加处理器
     * ctx.fireChannelRead() 作用是将数据从处理器传递到下一个处理器，没有这个，后面的处理器不会调用
     * @param socketChannel
     * @throws Exception
     */
    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline pipeline = socketChannel.pipeline();

        // TODO 目前添加该处理器之后，其他处理器不执行，应该是ctx.fireChannelRead()未执行，原因待分析？？？
        //  http请求编解码器,请求解码，响应编码 ChannelInboundHandlerAdapter,只能处理get请求，其他请求执行该处理器之后无法继续执行
//        pipeline.addLast(new HttpServerCodec());

        //http请求报文聚合为完整报文，最大请求报文为10M ChannelInboundHandlerAdapter
        pipeline.addLast(new HttpObjectAggregator(10 * 1024 * 1024));
        //响应报文压缩  ChannelInboundHandlerAdapter
        pipeline.addLast(new HttpContentCompressor());
        //业务处理handler  ChannelInboundHandlerAdapter
        pipeline.addLast(new StringDecoder());

//        pipeline.addLast(new HttpServerChannelAdapter());
        // TODO 添加方式顺序一：
        //  如何该handler在HttpServerChannelHandler之前添加，要在HttpServerChannelHandler 之后执行，
        //  需要在 HttpServerChannelHandler中使用ctx.writeAndFlush(responseMsg);返回信息
        pipeline.addLast(new StringEncoder());
        // TODO ChannelInboundHandlerAdapter
        pipeline.addLast(new HttpServerChannelHandler());

        // ChannelOutboundHandlerAdapter
        // TODO 添加方式顺序二：
        //  如何该handler在HttpServerChannelHandler之后添加，要在HttpServerChannelHandler 之后执行，
        //  需要在 HttpServerChannelHandler中使用ctx.channel().writeAndFlush(responseMsg);返回信息
//        pipeline.addLast(new StringEncoder());
    }


}
