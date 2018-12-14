package com.pghome.enums.order;

import lombok.Getter;

/**
 * @Auther: tianws
 * @Date: 2018/11/29 16:01
 * @Description:
 */
@Getter
public enum  PayStatusEnum {
    WAIT(0,"等待支付"),
    SUCESS(1,"支付成功"),
    ;


    private Integer code;
    private String message;

    PayStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
