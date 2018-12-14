package com.pghome.enums.order;

import lombok.Getter;

/**
 * @Auther: tianws
 * @Date: 2018/11/29 15:54
 * @Description:
 */
@Getter
public enum  OrderStatusEnum {
    NEW(0,"新订单"),
    FINISHED(1,"完结"),
    CAHCEL(2,"已取消");

    private Integer code;

    private String message;

    OrderStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
