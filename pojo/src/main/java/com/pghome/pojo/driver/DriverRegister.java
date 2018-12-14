package com.pghome.pojo.driver;

import com.pghome.enums.driver.StatusEnum;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * @Auther: tianws
 * @Date: 2018/12/7 11:14
 * @Description:
 */
@Getter
@Setter
@ToString
public class DriverRegister {
    //id
    private Integer driverId;
    //司机姓名
    private String driverName;
    //手机号
    private String phone;
    //证件类型 默认身份证号
    private Integer type;
    //证件号码
    private String number;
    //初次领取驾驶照日期
    private Date drivingDate;
    //车牌号
    private String carNumber;
    //车型
    private String carModel;
    //车辆颜色
    private String carColor;
    //车辆所有人
    private String carName;
    //车辆注册日期
    private Date carRegisterDate;
    //审核状态 0-审核中 ,1-审核失败 2-审核成功
    private Integer status;
    //修改日期
    private Date updateTime;
    //创建日期
    private Date createTime;
}
