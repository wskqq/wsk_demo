package example.netty.server;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

import java.nio.charset.Charset;
import java.util.Map;

/**
 * 服务端接收消息的自定义处理类父类为：
 *      ChannelInboundHandlerAdapter
 * 客户端发送消息的自定义处理类父类为：
 *      SimpleChannelInboundHandler
 *  在初始化类中添加过处理类之后，在处理类中添加自己的业务逻辑
 */
/**
 * @Description TODO
 * @Author acer
 * @Date 2022/3/3 10:39
 * @Version 1.0
 */
public class HttpServerChannelAdapter extends ChannelInboundHandlerAdapter {
    private HttpRequest request;
    private FullHttpResponse response;
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        //判断是不是http请求
        if(msg instanceof FullHttpRequest){
//            HttpRequest httpRequest = (HttpRequest) msg;
//            parseUri(httpRequest);
//            parseHttpMethod(httpRequest);
//            parseHttpHeaders(httpRequest);
//            parseBody(httpRequest);
//            FullHttpResponse res = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, Unpooled.copiedBuffer("msg", CharsetUtil.UTF_8));
//            res.headers().set(HttpHeaderNames.CONTENT_TYPE,"text/plain;charset=utf-8");
//            HttpUtil.setContentLength(res, res.content().readableBytes());
//            ctx.writeAndFlush(res).addListener(ChannelFutureListener.CLOSE);

            //获取httpRequest
            FullHttpRequest httpRequest = (FullHttpRequest) msg;
            try{
                String uri = httpRequest.uri();
                String content = httpRequest.content().toString(CharsetUtil.UTF_8);
                HttpMethod method = httpRequest.method();

                //响应
                String responseMsg = "Hello World";
                FullHttpResponse response = new DefaultFullHttpResponse(
                        HttpVersion.HTTP_1_1,HttpResponseStatus.OK,
                        Unpooled.copiedBuffer(responseMsg + "\n",CharsetUtil.UTF_8)
                );
                response.headers().set(HttpHeaderNames.CONTENT_TYPE,"text/plain;charset=UTF-8");
                ctx.writeAndFlush(response).addListener(ChannelFutureListener.CLOSE);
            }finally {
                httpRequest.release();
            }
            super.channelRead(ctx, msg);
        }
    }
    /**
     * 获得请求方式
     * @param httpRequest
     */
    private HttpMethod parseHttpMethod(HttpRequest httpRequest){
        HttpMethod httpMethod = httpRequest.method();
        System.out.println("method:"+httpMethod.name());
        return httpMethod;
    }
    /**
     * 打印头部信息
     * @param httpRequest
     */
    private HttpHeaders parseHttpHeaders(HttpRequest httpRequest){
        HttpHeaders httpHeaders = httpRequest.headers();
        for (Map.Entry<String, String> entry : httpHeaders.entries()) {
            System.out.println(entry.getKey()+":"+entry.getValue());
        }
        return httpHeaders;
    }
    /**
     * 打印请求地址
     * @param httpRequest
     */
    private void parseUri(HttpRequest httpRequest){
        String uri = httpRequest.uri();
        System.out.println("uri:"+uri);
    }
    /**
     * 打印请求体
     * @param httpRequest
     */
    private void parseBody(HttpRequest httpRequest){
        if(httpRequest instanceof HttpContent){
            HttpContent httpContent = (HttpContent) httpRequest;
            System.out.println("content:"+httpContent.content().toString(Charset.defaultCharset()));
        }
    }
}