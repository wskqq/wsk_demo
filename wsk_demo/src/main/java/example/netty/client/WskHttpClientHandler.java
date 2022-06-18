package example.netty.client;

import com.sun.jndi.toolkit.url.Uri;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

import java.net.URI;

/**
 * 客户端发送消息的自定义处理类
 */
/**
 * @Description TODO
 * @Author acer
 * @Date 2022/3/3 10:39
 * @Version 1.0
 */
public class WskHttpClientHandler extends SimpleChannelInboundHandler<FullHttpResponse> {
    /**
     * 客户端发送消息
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        String url = "http://127.0.0.1:8889/";
        URI uri = new URI(url);
        String sendMsg = "Hello World! 你好世界";
        // 组装请求
        FullHttpRequest fullHttpRequest = new DefaultFullHttpRequest(HttpVersion.HTTP_1_1, HttpMethod.GET,
                uri.toASCIIString(), Unpooled.wrappedBuffer(sendMsg.getBytes(CharsetUtil.UTF_8)));
        // 设置请求报文长度
        fullHttpRequest.headers().set(HttpHeaderNames.CONTENT_LENGTH,fullHttpRequest.content().readableBytes());
        // 发送http请求
        ctx.channel().writeAndFlush(fullHttpRequest);
    }

    /**
     * 接收服务端返回数据
     * @param channelHandlerContext
     * @param fullHttpResponse
     * @throws Exception
     */
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, FullHttpResponse fullHttpResponse) throws Exception {
        ByteBuf byteBuf = fullHttpResponse.content();
        System.out.println(byteBuf.toString(CharsetUtil.UTF_8));
    }
}
