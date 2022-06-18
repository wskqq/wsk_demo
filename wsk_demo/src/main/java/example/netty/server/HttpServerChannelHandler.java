package example.netty.server;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description TODO
 * @Author wsk
 * @Date 2022/4/28 16:04
 * @Version 1.0
 */
public class HttpServerChannelHandler extends SimpleChannelInboundHandler<FullHttpRequest> {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ctx.flush();
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, FullHttpRequest fullHttpRequest) throws Exception {
        String uri = fullHttpRequest.uri();
        Map<String,String> resMap = new HashMap<>(4);
        resMap.put("uri",uri);
        resMap.put("method",fullHttpRequest.method().name());
        String msg = "<html><head><title>test</title></head><body>只是测试</body></html>";
        FullHttpResponse fullHttpResponse = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1,
                HttpResponseStatus.OK, Unpooled.copiedBuffer(msg, CharsetUtil.UTF_8));

        // 设置请求头信息
        fullHttpResponse.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/plain;charset=UTF-8");

        //将信息返回给客户端
        channelHandlerContext.writeAndFlush(fullHttpResponse).addListener(ChannelFutureListener.CLOSE);
    }
}
