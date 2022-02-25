package example.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandler;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

public class HttpServerChannelHandler extends SimpleChannelInboundHandler<FullHttpRequest> {

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, FullHttpRequest fullHttpRequest) throws Exception {
            String uri = fullHttpRequest.uri();
            System.out.println("URI:" + uri);
            String msg = "<html><head><title>test</title></head><body>你请求uri为：" + uri+"</body></html>";
            ByteBuf byteBufOne = Unpooled.copiedBuffer(msg, CharsetUtil.UTF_8);
            FullHttpResponse fullHttpResponse = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1,HttpResponseStatus.OK,byteBufOne);
            fullHttpResponse.headers().add(HttpHeaderNames.CONTENT_TYPE,"text/html");
//            fullHttpResponse.headers().add(HttpHeaderNames.CONTENT_LENGTH,byteBufOne.readableBytes());

            channelHandlerContext.writeAndFlush(fullHttpResponse).addListener(ChannelFutureListener.CLOSE);
    }
}
