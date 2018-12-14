package com.pghome.constant;

/**
 * @Auther: tianws
 * @Date: 2018/11/23 10:58
 * @Description: token获取返回错误
 */
public class WeChatTokenRestContants {

    //获取到的凭证
    private String access_token;

    //	凭证有效时间，单位：秒
    private String expires_in;

    //错误编码
    private String errcode;
    //错误信息
    private String errmsg;


    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(String expires_in) {
        this.expires_in = expires_in;
    }

    public String getErrcode() {
        return errcode;
    }

    public void setErrcode(String errcode) {
        this.errcode = errcode;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }
}
