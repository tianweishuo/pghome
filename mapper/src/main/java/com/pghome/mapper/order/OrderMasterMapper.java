package com.pghome.mapper.order;

import com.pghome.pojo.order.OrderMaster;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Auther: tianws
 * @Date: 2018/11/29 15:52
 * @Description:
 */
public interface OrderMasterMapper {
    /**
     * 新增订单
     * @param orderMaster
     * @return
     */
    int saveOrderMaster(OrderMaster orderMaster);

    /**
     * 根据openid查询订单
     * @param buyerOpenid
     * @return
     */
    List<OrderMaster> findByBuyerOpenid(@Param("buyerOpenid") String buyerOpenid);

    /**
     * 查询单个订单
     * @param orderId
     * @return
     */
    OrderMaster findOne(@Param("orderId") String orderId);

    //修改订单状态
    int updateOrderStatus(@Param("orderId") String orderId,@Param("orderStatus") Integer orderStatus);

    //修改支付状态
    int updatePayStatus(@Param("orderId") String orderId,@Param("payStatus") Integer payStatus);

}
