package com.pghome.service.impl;

import com.pghome.dto.order.OrderDTO;
import com.pghome.exception.PGException;
import com.pghome.exception.ResultEnum;
import com.pghome.service.BuyerService;
import com.pghome.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Auther: tianws
 * @Date: 2018/12/3 14:18
 * @Description:
 */
@Service
public class BuyerServiceImpl implements BuyerService {

    private static final Logger logger = LoggerFactory.getLogger(BuyerServiceImpl.class);

    @Autowired
    private OrderService orderService;

    /**
     * 查询订单
     * @param openid
     * @param orderId
     * @return
     */
    @Override
    public OrderDTO findOrderOne(String openid, String orderId) {
        return checkOrderOwner(openid,orderId);
    }

    /**
     * 取消订单
     * @param openid
     * @param orderId
     * @return
     */
    @Override
    public OrderDTO cancelOrder(String openid, String orderId) {
        OrderDTO orderDTO = checkOrderOwner(openid,orderId);
        if(openid == null){
            logger.error("【取消订单】查询不到该订单,orderId={}",orderId);
            throw new PGException(ResultEnum.ORDER_NOT_EXISTS);
        }
        return orderService.cancel(orderDTO);
    }

    private OrderDTO checkOrderOwner(String openid, String orderId){
        OrderDTO orderDTO = orderService.findOne(orderId);
        if(orderDTO == null){
            return null;
        }
        //判断是否是自己的订单
        if(!orderDTO.getBuyerOpenid().equalsIgnoreCase(openid)){
            logger.error("【查询订单】订单的openid不一致,openid={},orderDTO={}",openid,orderDTO);
            throw new PGException(ResultEnum.PRODUCT_STOCK_ERROR);
        }
        return orderDTO;
    }

}
