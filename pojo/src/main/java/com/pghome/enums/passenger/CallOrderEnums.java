package com.pghome.enums.passenger;

import lombok.Getter;

/**
 * @Auther: tianws
 * @Date: 2018/12/21 11:28
 * @Description: 呼叫订单状态
 */
@Getter
public enum  CallOrderEnums {

    //订单状态 1-呼叫中,2-等待司机,3-行驶中,4-行程结束,0-订单取消
    CALL_IN(1,"呼叫中"),
    WAIT_IN(2,"等待司机中"),
    TRAVEL_IN(3,"行驶中"),
    ORDER_END(4,"订单结束"),
    ORDER_CANCEL(5,"订单取消")
    ;


    private Integer code;

    private String message;

    CallOrderEnums(Integer code,String message){
        this.code = code;
        this.message = message;
    }





}
