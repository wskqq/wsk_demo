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
     * 读取客户端返回的数据，telnet通之后，send发送数据执行该方法
     * @param ctx
     * @param s
     * @throws Exception
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String s) throws Exception {
        System.out.println("channelRead0》》》》》》》》》》》》》开始");
        System.out.println("接收到客户端数据[" + s + "]");
        String responseMsg = "Hello World";

        // TODO 两种返回数据的方式与InboundHandler与outboundHandler添加顺序有关，
        //  顺序与调用方式不匹配会导致，Handler不执行
        // TODO Ctx.write()：仅仅是将我们的信息加入到队列里面，并没有发生出去。是在当前handler寻找下一个handler，并不是将这个pipline重新走了一遍
        //  Ctx.writeAndFlush()：是向前找到第一个遇到的OutHandler，再发送出去，也并不是将这个pipline重新走了一遍。
        //  Ctx.channel.writeAndFlush()：这个是将这个pipline重新走了一遍，可能会引起死循环，假如这个handler是中间的handler，
        //  他会将这个pipline重新走了一遍，等走到原点，又会有机会执行Ctx.channel.writeAndFlush()，这个大部分是用在客户端。
        ctx.writeAndFlush(responseMsg);
//        ctx.channel().writeAndFlush(responseMsg);

        System.out.println("服务端响应数据结束》》》》》》》》》》》");
    }

    /**
     * 客户端发送消息, telnet命令执行该方法
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
