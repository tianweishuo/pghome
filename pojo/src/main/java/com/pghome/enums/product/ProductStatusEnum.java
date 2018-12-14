package com.pghome.enums.product;

import lombok.Getter;

/**
 * @Auther: tianws
 * @Date: 2018/11/29 15:55
 * @Description:
 */
@Getter
public enum  ProductStatusEnum {
    UP(0,"在架"),
    DOWN(1,"下架");

    private Integer code;
    private String message;

    ProductStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
