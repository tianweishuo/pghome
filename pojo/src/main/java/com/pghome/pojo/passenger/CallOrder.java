package com.pghome.pojo.passenger;

import com.pghome.enums.order.PayStatusEnum;
import com.pghome.enums.passenger.CallOrderEnums;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Auther: tianws
 * @Date: 2018/12/14 10:28
 * @Description: 叫车订单
 */
@Getter
@Setter
@ToString
public class CallOrder {
    //订单id
    private String callOrderId;
    //手机号
    private String callPhone;
    //开始位置
    private String startPositionName;
    //开始纬度
    private String startLatitude;
    //开始经度
    private String startLongitude;
    //结束位置
    private String endPositionName;
    //结束纬度
    private String endLatitude;
    //结束经度
    private String endLongitude;
    //订单状态 1-呼叫中,2-等待司机,3-行驶中,4-行程结束,0-订单取消
    private Integer orderStatus = CallOrderEnums.CALL_IN.getCode();
    //订单结束状态 1-未发起结算未支付,1-已发起结算未支付,3-已支付
    private Integer payStatus = PayStatusEnum.UNSENT.getCode();
    //订单金额 默认为0
    private BigDecimal orderAmount = BigDecimal.ZERO;
    //叫车时间
    private Date callTime;
    //订单创建时间
    private Date updateTime;
}
