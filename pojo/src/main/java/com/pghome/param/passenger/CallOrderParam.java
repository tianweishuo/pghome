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
    private String phone;
    //开始纬度
    @NotBlank(message = "开始纬度不能为空")
    private String startLatitude;
    //开始经度
    @NotBlank(message = "开始经度不能为空")
    private String startLongitude;
    //结束纬度
    @NotBlank(message = "结束纬度不能为空")
    private String endLatitude;
    //结束经度
    @NotBlank(message = "结束经度不能为空")
    private String endLongitude;

}
