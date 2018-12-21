package com.pghome.service.impl;

import com.alibaba.fastjson.JSON;
import com.dyuproject.protostuff.runtime.RuntimeSchema;
import com.pghome.exception.PGException;
import com.pghome.exception.ResultEnum;
import com.pghome.idworker.Sid;
import com.pghome.mapper.passenger.CallOrderMapper;
import com.pghome.param.passenger.CallOrderParam;
import com.pghome.pojo.passenger.CallOrder;
import com.pghome.service.PassengerService;
import com.pghome.utils.ProtoStuffUtil;
import com.pghome.utils.RedisClient;
import com.pghome.utils.RedisOperator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.UUID;

/**
 * @Auther: tianws
 * @Date: 2018/12/14 16:24
 * @Description:
 */
@Slf4j
@Service
public class PassengerServiceImpl implements PassengerService {

    @Autowired
    private Sid sid;

    @Autowired
    private RedisClient redisClient;

    @Autowired
    private RedisOperator redisOperator;

    private RuntimeSchema<CallOrder>  orderRuntimeSchema = RuntimeSchema.createFrom(CallOrder.class);

    @Autowired
    private CallOrderMapper callOrderMapper;

    /**
     * 创建叫车订单
     * @param param
     * @return
     */
    @Transactional
    @Override
    public String createCallOrder(CallOrderParam param) {
        log.info("【创建叫车订单】开始:");
        CallOrder order = new CallOrder();
        BeanUtils.copyProperties(param,order);
        order.setCallOrderId(sid.nextShort());
        order.setCallTime(new Date());
        order.setUpdateTime(new Date());
        try {
            //创建订单
            callOrderMapper.create(order);
            log.info("【创建叫车订单】:订单={}",order);
            //存入缓存
            //redisClient.set(order.getCallOrderId(),order);
            byte[] serialize = ProtoStuffUtil.serialize(order);
            System.out.println(serialize);
            CallOrder deserialize = ProtoStuffUtil.deserialize(serialize, CallOrder.class);
            System.out.println(deserialize);
            redisOperator.set(order.getCallOrderId(), JSON.toJSONString(order));
            //调用websocket发送通知

        }catch (Exception e){
            e.printStackTrace();
            log.error("【创建叫车订单】错误,errorInfo={}",e.getMessage());
            throw new PGException(ResultEnum.CREATE_ORDER_ERR);
        }



        return order.getCallOrderId();
    }
}
