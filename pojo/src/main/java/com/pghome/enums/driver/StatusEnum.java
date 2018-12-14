package com.pghome.enums.driver;

import lombok.Getter;

/**
 * @Auther: tianws
 * @Date: 2018/12/7 10:32
 * @Description: 司机审核状态
 */
@Getter
public enum  StatusEnum {
    WAIT(0,"等待审核中"),
    FAILURE(1,"审核失败"),
    SUCCESS(2,"审核通过")
    ;

    private Integer code;

    private String message;

    StatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
