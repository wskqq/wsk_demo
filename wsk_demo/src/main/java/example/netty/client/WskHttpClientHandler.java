package example.netty.client;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @Description 客户端发送消息的自定义处理类
 * @Author acer
 * @Date 2022/3/3 10:39
 * @Version 1.0
 */
public class WskHttpClientHandler extends SimpleChannelInboundHandler<String> {

    /**
     * 接收服务端返回数据
     * @param channelHandlerContext
     * @param msg
     * @throws Exception
     */
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, String msg) throws Exception {
        System.out.println("客户端接收到服务端返回数据channelRead0的信息为[" + msg + "]");
    }

    /**
     * 向服务端发送数据
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("客户端调用channelActive方法");
        String msg = "客户端发送服务端信息";
        ctx.writeAndFlush(msg);
    }
}
