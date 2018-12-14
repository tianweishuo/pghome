package com.pghome.mq;

import com.alibaba.rocketmq.client.consumer.DefaultMQPushConsumer;
import com.alibaba.rocketmq.common.consumer.ConsumeFromWhere;

/**
 * @Auther: tianws
 * @Date: 2018/12/14 15:46
 * @Description:
 */
public class Consumer {

    private static DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("ConsumerGroupName");
    private static int initialState = 0;

    private Consumer() {

    }

    public static DefaultMQPushConsumer getDefaultMQPushConsumer(){
        if(consumer == null){
            consumer = new DefaultMQPushConsumer("ConsumerGroupName");
        }

        if(initialState == 0){
            consumer.setNamesrvAddr("192.168.203.10:9876");
            consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
            initialState = 1;
        }

        return consumer;
    }

}
