package example.netty.server;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @Description 官方Demo：服务端自定义处理器
 * @Author wsk
 * @Date 2022/4/28 16:04
 * @Version 1.0
 */
public class HttpServerChannelHandler extends SimpleChannelInboundHandler<String> {

    /**
     * 读取客户端返回的数据
     * @param ctx
     * @param s
     * @throws Exception
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String s) throws Exception {
        System.out.println("channelRead0》》》》》》》》》》》》》开始");
        System.out.println("接收到客户端数据[" + s + "]");
        String responseMsg = "Hello World";
        ctx.writeAndFlush(responseMsg);
        System.out.println("服务端响应数据结束》》》》》》》》》》》");
    }

    /**
     * 客户端发送消息
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("服务端channelActive方法》》》》》》》》》》[" + ctx.name() + "]");
        String responseMsg = "channelActive ";
        ctx.writeAndFlush(responseMsg);
    }

    /**
     * 异常处理方法
     * @param ctx
     * @param cause
     * @throws Exception
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("服务端exceptionCaught异常处理》》》》》");
        cause.printStackTrace();
        ctx.close();
    }

}
