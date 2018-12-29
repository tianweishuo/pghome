package com.pghome.hellonetty;

import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;
import org.jboss.netty.handler.codec.string.StringDecoder;
import org.jboss.netty.handler.codec.string.StringEncoder;

import java.net.InetSocketAddress;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @Auther: tianws
 * @Date: 2018/12/24 10:05
 * @Description:
 */
public class Service {


    public static void main(String[] args) {
        //服务类
        ServerBootstrap bootstrap = new ServerBootstrap();

        ExecutorService boss = Executors.newCachedThreadPool();
        ExecutorService worker = Executors.newCachedThreadPool();

        //设置niosocket工厂
        bootstrap.setFactory(new NioServerSocketChannelFactory(boss,worker));
        //设置管道的工厂
        bootstrap.setPipelineFactory(new ChannelPipelineFactory() {
            @Override
            public ChannelPipeline getPipeline() throws Exception {
                ChannelPipeline pipeline = Channels.pipeline();
                //解析接受信息为String
                pipeline.addLast("decoder",new StringDecoder());
                //返回直接使用string
                pipeline.addLast("encoder",new StringEncoder());
                pipeline.addLast("helloHandler",new HelloHandler());
                return pipeline;
            }
        });

        //绑定端口
        bootstrap.bind(new InetSocketAddress(10101));
        System.out.println("start!!");
    }


}
