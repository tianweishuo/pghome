package com.pghome.dto;

import lombok.Data;

/**
 * @Auther: tianws
 * @Date: 2018/11/30 10:25
 * @Description: 购物车
 */
@Data
public class CartDTO {
    //商品id
    private String productId;
    //商品数量
    private Integer productQuantity;

    public CartDTO(String productId, Integer productQuantity) {
        this.productId = productId;
        this.productQuantity = productQuantity;
    }
}
