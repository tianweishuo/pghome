package com.pghome.pojo.product;

import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Auther: tianws
 * @Date: 2018/11/29 14:06
 * @Description:
 */
@Data
@ToString
public class ProductInfo {

    //商品id
    private String productId;
    //商品名称
    private String productName;
    //单价
    private BigDecimal productPrice;
    //库存
    private Integer productStock;
    //描述
    private String productDescription;
    //小图片
    private String productIcon;
    //商品状态 0正常 1下架
    private Integer productStatus;
    //类目编号
    private Integer categoryType;
    //创建时间
    private Date createTime;
    //更新时间
    private Date updateTime;
}
