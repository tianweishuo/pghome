package com.pghome.mq;

import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.client.producer.DefaultMQProducer;

/**
 * @Auther: tianws
 * @Date: 2018/12/14 15:45
 * @Description:
 */
public class Producer {

    private static DefaultMQProducer producer = new DefaultMQProducer("ProducerGroupName");
    private static int initialState = 0;

    private Producer() {

    }
    public static DefaultMQProducer getDefaultMQProducer(){
        if(producer == null){
            producer = new DefaultMQProducer("ProducerGroupName");
        }
        if(initialState == 0){
            producer.setNamesrvAddr("192.168.203.10:9876");
            try {
                producer.start();
            } catch (MQClientException e) {
                e.printStackTrace();
                return null;
            }

            initialState = 1;
        }

        return producer;
    }




}
