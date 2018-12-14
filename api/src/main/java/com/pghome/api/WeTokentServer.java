package com.pghome.api;

import com.alibaba.fastjson.JSON;
import com.pghome.constant.WechatApiUrlConstants;
import com.pghome.constant.WechatContants;
import com.pghome.utils.HttpUtils;
import com.pghome.utils.RedisOperator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: tianws
 * @Date: 2018/11/22 15:10
 * @Description:
 */
public class WeTokentServer implements ApplicationListener<ContextRefreshedEvent> {

    private static final Logger logger = LoggerFactory.getLogger(WeTokentServer.class);


    private RedisOperator redisOperator;


    private WechatContants wechatContants;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
    }
}
