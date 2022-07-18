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
        System.out.println("服务端接收到的数据：" + msg);

        //通过编解码器把byteBuf解析成FullHttpRequest
        if (msg instanceof FullHttpRequest) {

            //获取httpRequest
            FullHttpRequest httpRequest = (FullHttpRequest) msg;

            try {
                //获取请求路径、请求体、请求方法
                String uri = httpRequest.uri();
                String content = httpRequest.content().toString(CharsetUtil.UTF_8);
                HttpMethod method = httpRequest.method();
                System.out.println("服务器接收到请求:");

                //响应
                String responseMsg = "Hello World";
                FullHttpResponse response = new DefaultFullHttpResponse(
                        HttpVersion.HTTP_1_1,HttpResponseStatus.OK,
                        Unpooled.copiedBuffer(responseMsg,CharsetUtil.UTF_8)
                );
                response.headers().set(HttpHeaderNames.CONTENT_TYPE,"text/plain;charset=UTF-8");
                ctx.writeAndFlush(response).addListener(ChannelFutureListener.CLOSE);
            } finally {
                httpRequest.release();
            }

        }

    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, FullHttpRequest fullHttpRequest) throws Exception {
        System.out.println("channelRead0》》》》》》》》》》》》》开始");
    }

}
