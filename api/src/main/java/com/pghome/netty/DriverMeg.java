package com.pghome.netty;

import lombok.Getter;
import lombok.Setter;

/**
 * @Auther: tianws
 * @Date: 2018/12/27 11:33
 * @Description:
 */
@Getter
@Setter
public class DriverMeg {

    private String senderId;		// 司机的id
    private String phone;           // 司机手机号
    private String latitude;		// 纬度值
    private String longitude;		// 经度值
}
