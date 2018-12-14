package com.pghome.service;

import com.pghome.dto.order.OrderDTO;

/**
 * @Auther: tianws
 * @Date: 2018/12/3 14:16
 * @Description: 买家
 */
public interface BuyerService {

    //查询一个订单
    OrderDTO findOrderOne(String openid,String orderId);
    //取消订单
    OrderDTO cancelOrder(String openid,String orderId);

}
