package com.pghome.netty;

import lombok.Getter;
import lombok.Setter;

/**
 * @Auther: tianws
 * @Date: 2018/12/27 11:31
 * @Description:
 */
@Setter
@Getter
public class CallMeg {
    private String senderId;		// 用户id号
    private String phone;           // 用户手机号
    private String callOrderId;     // 叫车订单ID
    private String startPositionName; //开始位置名称
    private String startLatitude;	// 上车纬度值
    private String startLongitude;	// 上车经度值
    private String endPositionName; //结束位置名称
    private String endLatitude;		// 下车纬度值
    private String endLongitude;	// 下车经度值
}
