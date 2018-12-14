package com.pghome.api;

import com.alibaba.fastjson.JSON;
import com.pghome.constant.WeChatTokenRestContants;
import com.pghome.constant.WechatApiUrlConstants;
import com.pghome.constant.WechatContants;
import com.pghome.utils.HttpUtils;
import com.pghome.utils.RedisOperator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: tianws
 * @Date: 2018/11/23 10:21
 * @Description: 微信的token定时获取
 */
public class WXTokenTask {

    private static final Logger logger = LoggerFactory.getLogger(WXTokenTask.class);


    private WechatContants wechatContants;


    private RedisOperator redisOperator;


    /**
     * 定时获取微信toiken
     */

    //@Scheduled(cron = "0 0 */2 * * ?")  //俩小时执行一次
    //@Scheduled(cron="0/5 * *  * * ? ")   //测试每5秒执行一次
    //@Scheduled(cron="0/7000 * *  * * ? ")   //每7000秒执行一次
    //@Scheduled(fixedRate=2000000) ////测试每20秒执行一次
    public void getAccessToken(){
        StopWatch watchRules = new StopWatch();
        logger.info("【获取token开始】");
        Map<String, Object> map = new HashMap<String,Object>();
        map.put("appid",wechatContants.getAppid());
        map.put("secret",wechatContants.getSecret());
        map.put("grant_type",wechatContants.getGranttype());
        logger.info("【获取token使用参数】:{}",JSON.toJSONString(map));
        watchRules.start();
        String response = HttpUtils.doGet(WechatApiUrlConstants.token, map);
        watchRules.stop();
        logger.info("【获取token结束】执行时间============={}ms",watchRules.getTotalTimeMillis());
        logger.info("【获取响应报文】==============:{}",JSON.toJSONString(response));
        WeChatTokenRestContants weChatTokenRestContants = JSON.parseObject(response, WeChatTokenRestContants.class);
        if(weChatTokenRestContants.getErrcode() != null){
            if("-1".equals(weChatTokenRestContants.getErrcode())){
                logger.error("【获取微信Token失败】系统繁忙，此时请开发者稍候再试====错误编码{},错误信息{}",weChatTokenRestContants.getErrcode(),weChatTokenRestContants.getErrmsg());
            }else if("0".equals(weChatTokenRestContants.getErrcode())){
                logger.error("【获取微信Token失败】请求成功====错误编码{},错误信息{}",weChatTokenRestContants.getErrcode(),weChatTokenRestContants.getErrmsg());
            }else if("40001".equals(weChatTokenRestContants.getErrcode())){
                logger.error("【获取微信Token失败】AppSecret错误或者AppSecret不属于这个公众号，请开发者确认AppSecret的正确性====错误编码{},错误信息{}",weChatTokenRestContants.getErrcode(),weChatTokenRestContants.getErrmsg());
            }else if("40002".equals(weChatTokenRestContants.getErrcode())){
                logger.error("【获取微信Token失败】请确保grant_type字段值为client_credential====错误编码{},错误信息{}",weChatTokenRestContants.getErrcode(),weChatTokenRestContants.getErrmsg());
            }else if("40164".equals(weChatTokenRestContants.getErrcode())){
                logger.error("【获取微信Token失败】调用接口的IP地址不在白名单中，请在接口IP白名单中进行设置。（小程序及小游戏调用不要求IP地址在白名单内。====错误编码{},错误信息{}",weChatTokenRestContants.getErrcode(),weChatTokenRestContants.getErrmsg());
            }else if("40013".equals(weChatTokenRestContants.getErrcode())){
                logger.error("【获取微信Token失败】appid错误====错误编码{},错误信息{}",weChatTokenRestContants.getErrcode(),weChatTokenRestContants.getErrmsg());
            }
        }else{
            redisOperator.set("weixin_token", JSON.toJSONString(response),7000);
        }


    }

}
