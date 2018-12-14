package com.pghome.mapper.order;

import com.pghome.pojo.order.OrderDetail;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Auther: tianws
 * @Date: 2018/11/29 15:53
 * @Description:
 */
public interface OrderDetailMapper {

    List<OrderDetail> findByOrderId(String orderId);

    int saveOrderDetail(OrderDetail orderDetail);

}
