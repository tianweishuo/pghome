package com.pghome.pojo.passenger;

import java.util.Date;

/**
 * @Auther: tianws
 * @Date: 2018/12/14 10:28
 * @Description: 叫车订单
 */
public class CallOrder {
    //订单id
    private String orderId;
    //手机号
    private String phone;
    //开始纬度
    private String startLatitude;
    //开始经度
    private String startLongitude;
    //开始位置
    private String startPosition;
    //结束纬度
    private String endLatitude;
    //结束经度
    private String endLongitude;
    //结束位置
    private String endPosition;
    //订单状态 1-呼叫中,2-等待司机,3-行驶中,4-行程结束,0-订单取消
    private Integer status;
    //叫车时间
    private Date callTime;
    //订单创建时间
    private Date updateTime;
}
