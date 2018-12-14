package com.pghome.param.driver;


import com.pghome.enums.driver.StatusEnum;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @Auther: tianws
 * @Date: 2018/12/7 09:47
 * @Description:
 */
@Getter
@Setter
@ToString
public class RegisterParam {
    //司机姓名
    @NotBlank(message = "名称不能为空")
    private String driverName;
    //手机号
    @NotBlank(message = "手机号不能为空")
    private String phone;
    //证件类型 默认身份证号
    private Integer type = 1;
    //证件号码
    @NotBlank(message = "身份证号不能为空")
    private String number;
    //初次领取驾驶照日期
    @NotNull(message = "初次领取驾照日期不能为空")
    private Date drivingDate;
    //车牌号
    @NotBlank(message = "车牌不能为空")
    private String carNumber;
    //车型
    @NotBlank(message = "车型不能为空")
    private String carModel;
    //车辆颜色
    @NotBlank(message = "车辆颜色不能为空")
    private String carColor;
    //车辆所有人
    @NotBlank(message = "车辆所有人不能为空")
    private String carName;
    //车辆注册日期
    @NotNull(message = "车辆注册日期不能为空")
    private Date carRegisterDate;
    //审核状态 0-审核中 ,1-审核失败 2-审核成功
    private Integer status = StatusEnum.WAIT.getCode();
}
