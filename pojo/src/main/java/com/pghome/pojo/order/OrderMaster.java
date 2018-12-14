package com.pghome.pojo.order;

import com.pghome.enums.order.OrderStatusEnum;
import com.pghome.enums.order.PayStatusEnum;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @Auther: tianws
 * @Date: 2018/11/29 15:44
 * @Description: 订单主表
 */
@Data
public class OrderMaster {

    private String orderId;
    //买家名字
    private String buyerName;
    //买家电话
    private String buyerPhone;
    //买家地址
    private String buyerAddress;
    //买家微信openid
    private String buyerOpenid;
    //订单总金额
    private BigDecimal orderAmount;
    //订单状态,默认0新下单
    private Integer orderStatus = OrderStatusEnum.NEW.getCode();
    //支付状态,默认0未支付
    private Integer payStatus = PayStatusEnum.WAIT.getCode();
    //创建时间
    private Date createTime;
    //更新时间
    private Date updateTime;
}
