package com.pghome.param.passenger;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

/**
 * @Auther: tianws
 * @Date: 2018/12/14 10:36
 * @Description:
 */
@Getter
@Setter
@ToString
public class CallOrderParam {
    //手机号
    @NotBlank(message = "手机好不能为空")
    private String callPhone;
    //上车位置名称
    @NotBlank(message = "上车位置名称不能为空")
    private String startPositionName;
    //上车纬度
    @NotBlank(message = "上车纬度不能为空")
    private String startLatitude;
    //上车经度
    @NotBlank(message = "上车经度不能为空")
    private String startLongitude;
    //下车位置名称
    @NotBlank(message = "下车位置名称不能为空")
    private String endPositionName;
    //下车纬度
    @NotBlank(message = "下车纬度不能为空")
    private String endLatitude;
    //下车经度
    @NotBlank(message = "下车经度不能为空")
    private String endLongitude;
}
