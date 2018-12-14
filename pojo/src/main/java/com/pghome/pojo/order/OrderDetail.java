package com.pghome.pojo.order;

import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Auther: tianws
 * @Date: 2018/11/29 15:48
 * @Description: 订单明细表
 */
@Data
@ToString
public class OrderDetail {

    /** id */
    private String detailId;
    /** 订单id */
    private String orderId;
    /** 商品id */
    private String productId;
    /** 商品名称 */
    private String productName;
    /** 单价 */
    private BigDecimal productPrice;
    /** 商品数量 */
    private Integer productQuantity;
    /** 商品小图 */
    private String productIcon;
    /** 创建时间 */
    private Date createTime;
    /** 更新时间 */
    private Date updateTime;

}
