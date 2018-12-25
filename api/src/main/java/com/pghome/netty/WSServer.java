package com.pghome.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.springframework.stereotype.Component;

/**
 * @Auther: tianws
 * @Date: 2018/12/19 15:41
 * @Description:
 */
@Component
public class WSServer {

    private ServerBootstrap server;
    private EventLoopGroup mainGroup;
    private EventLoopGroup subGroup;
    private ChannelFuture future;

    private static class SingletionWSServer {
        static final WSServer instance = new WSServer();
    }

    public static WSServer getInstance() {
        return SingletionWSServer.instance;
    }

    public WSServer() {
        server = new ServerBootstrap();
        mainGroup = new NioEventLoopGroup();
        subGroup = new NioEventLoopGroup();
        server.group(mainGroup, subGroup)
                .channel(NioServerSocketChannel.class)
                .childHandler(new WSServerInitialzer());
    }

    public void start() {
        this.future = server.bind(8088);
        System.err.println("netty websocket server 启动完毕...");
    }



}
