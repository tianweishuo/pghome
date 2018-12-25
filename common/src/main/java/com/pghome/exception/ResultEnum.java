package com.pghome.exception;

/**
 * @Auther: tianws
 * @Date: 2018/11/23 15:26
 * @Description: 异常
 */
public enum ResultEnum {

    PARAM_ERROR(1,"参数不正确"),
    CART_EMPTY(2,"购物车不能为空"),

    WEIXIN_TOKEN_NULL(10001,"微信token在redis中获取为空,请联系管理员!"),
    PRODUCT_NOT_EXISTS(10,"商品不存在"),
    PRODUCT_STOCK_ERROR(11,"商品库存不正确"),
    ORDER_NOT_EXISTS(12,"订单不存在"),
    ORDERDETAIL_NOT_EXISTS(13,"订单详情不存在"),
    ORDER_STATUS_ERROR(14,"订单状态不正确"),
    ORDER_UPDATE_FAIL(15,"订单更新失败"),
    ORDER_DETAIL_EMPTY(16,"订单详情为空"),
    ORDER_PAY_STATUS_ERROR(17,"订单状态不正确"),
    ORDER_OWNER_ERROR(19,"该订单不属于当前用户"),
    //车辆注册错误 2001XX
    DRIVER_REGISTER_PHONE_EXISTS(200101,"注册手机号已存在"),
    DRIVER_REGISTER_CARNUMBER_EXISTS(200102,"注册车牌号已存在"),
    //叫车错误 3001xx
    CREATE_ORDER_ERR(3001001,"创建叫车订单失败"),
    //司机登录注册 4001xx
    LOGIN_USERNAME_NOT_NULL(4001001,"用户名不能为空"),
    LOGIN_PASSWORD_NOT_NULL(4001002,"密码不能为空"),
    LOGIN_CID_NOT_NOLL(4001003,"cid不能为空"),
    DRIVER_DOES_NOT_EXIST(4001004,"账号不存在"),
    PASSWORD_ERROR(4001005,"密码错误");

    private Integer code;

    private String message;

    ResultEnum(Integer code, String message){
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
