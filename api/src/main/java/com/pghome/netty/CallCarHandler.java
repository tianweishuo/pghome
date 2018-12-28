package com.pghome.netty;

import com.alibaba.fastjson.JSON;
import com.pghome.SpringUtil;
import com.pghome.enums.driver.MsgActionEnum;
import com.pghome.pojo.passenger.CallOrder;
import com.pghome.utils.JsonUtils;
import com.pghome.utils.RedisOperator;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.concurrent.GlobalEventExecutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.Set;

/**
 * @Auther: tianws
 * @Date: 2018/12/27 09:24
 * @Description: 呼叫车辆
 */
public class CallCarHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {

    private static final Logger logger = LoggerFactory.getLogger(ChatHandler.class);

    public static ChannelGroup users = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    /**
     * 覆盖了 channelRead0() 事件处理方法。
     * 每当从服务端读到客户端写入信息时，
     * 其中如果你使用的是 Netty 5.x 版本时，
     * 需要把 channelRead0() 重命名为messageReceived()
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {
        // 获取客户端传输过来的消息
        String content = msg.text();
        Channel currentChannel = ctx.channel();
        // 1. 获取客户端发来的消息
        logger.info("webscoket消息内容:{}",content);
        // 2. 判断消息类型，根据不同的类型来处理不同的业务
        DataContent dataContent = JSON.parseObject(content, DataContent.class);
        Integer action = dataContent.getAction();
        if(action.equals(MsgActionEnum.CONNECT.type)){
            //首次连接
            //2.1  当websocket 第一次open的时候，初始化channel，把用的channel和userid关联起来
            String senderId = dataContent.getDriverMeg().getSenderId();
            DriverChannelRel.put(senderId, currentChannel);
        }else if(action.equals(MsgActionEnum.CALL_ORDER.type)){
            RedisOperator redisOperator = (RedisOperator) SpringUtil.getBean("redisOperator");
            //叫车订单封装
            String senderId = dataContent.getCallMeg().getSenderId();
            String callOrderId = dataContent.getCallMeg().getCallOrderId();
            String callOrder = redisOperator.get(callOrderId);
            CallOrder order = JSON.parseObject(callOrder, CallOrder.class);
            dataContent.getCallMeg().setStartPositionName(order.getStartPositionName());
            dataContent.getCallMeg().setStartLatitude(order.getStartLatitude());
            dataContent.getCallMeg().setStartLongitude(order.getStartLongitude());
            dataContent.getCallMeg().setEndPositionName(order.getEndPositionName());
            dataContent.getCallMeg().setEndLatitude(order.getEndLatitude());
            dataContent.getCallMeg().setEndLongitude(order.getEndLongitude());
            UserChannelRel.put(senderId,currentChannel);
            Set<Channel> all = DriverChannelRel.getAll();
            if(all != null && all.size() > 0){
                int count = 0;
                //TODO 优化寻找小于等于设置公里范围内的司机,并发送通知
                for(Channel channel:all){
                    count++;
                    currentChannel.writeAndFlush(new TextWebSocketFrame(String.valueOf(count)));
                    channel.writeAndFlush(new TextWebSocketFrame("新订单,开始:"+JsonUtils.objectToJson(dataContent.getCallMeg())));
                }
            }else {
                currentChannel.writeAndFlush(new TextWebSocketFrame("已通知了:0个司机"));
            }

        }else if(action.equals(MsgActionEnum.KEEPALIVE.type)){
            //2.4 心跳类型的消息
            logger.info("收到来自channel为【{}】的心跳包",currentChannel);
            //TODO 更新最新定位
        }
    }

    /**
     * 覆盖channelActive 方法在channel被启用的时候触发（在建立连接的时候）
     * 覆盖了 channelActive() 事件处理方法。服务端监听到客户端活动
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
    }

    /**
     * 当客户端连接服务端之后（打开连接）
     * 获取客户端的channle，并且放到ChannelGroup中去进行管理
     * @param ctx
     * @throws Exception
     */
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        users.add(ctx.channel());
    }

    /**
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        String channelId = ctx.channel().id().asLongText();
        logger.info("客户端被移除,channelId为:{}",channelId);
        // 当触发handlerRemoved，ChannelGroup会自动移除对应客户端的channel
        users.remove(ctx.channel());
    }

    /**
     *
     * @param ctx
     * @param cause
     * @throws Exception
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        // 发生异常之后关闭连接（关闭channel），随后从ChannelGroup中移除
        ctx.channel().close();
        users.remove(ctx.channel());
    }




}
