package com.pghome.hellonetty;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.*;

/**
 * @Auther: tianws
 * @Date: 2018/12/24 10:11
 * @Description:
 */
public class HelloHandler extends SimpleChannelHandler {

    /**
     * 接受消息
     * @param ctx
     * @param e
     * @throws Exception
     */
    @Override
    public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) throws Exception {
        System.out.println("messageReceived");
        //读取数据
        String message = (String) e.getMessage();
        System.out.println(message);

        //回写数据
        ChannelBuffer buffer = ChannelBuffers.copiedBuffer("hi".getBytes());
        ctx.getChannel().write(buffer);
        super.messageReceived(ctx, e);
    }

    /**
     * 捕获异常
     * @param ctx
     * @param e
     * @throws Exception
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e) throws Exception {
        System.out.println("exceptionCaught");
        super.exceptionCaught(ctx, e);
    }

    /**
     * 连接打开
     * @param ctx
     * @param e
     * @throws Exception
     */
    @Override
    public void channelOpen(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
        System.out.println("channelOpen");
        super.channelOpen(ctx, e);
    }

    /**
     * 新建立连接
     * @param ctx
     * @param e
     * @throws Exception
     */
    @Override
    public void channelConnected(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
        System.out.println("channelConnected");
        super.channelConnected(ctx, e);
    }

    /**
     * channel关闭的时候触发
     * @param ctx
     * @param e
     * @throws Exception
     */
    @Override
    public void channelClosed(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
        System.out.println("channelClosed");
        super.channelClosed(ctx, e);
    }

    /**
     * 必须是连接已经建立,关闭通道的时候才会触发
     * @param ctx
     * @param e
     * @throws Exception
     */
    @Override
    public void channelDisconnected(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
        System.out.println("channelDisconnected");
        super.channelDisconnected(ctx, e);
    }
}
