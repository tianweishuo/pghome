package com.pghome.constant;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @Auther: tianws
 * @Date: 2018/11/22 16:55
 * @Description:
 */
@Component
@ConfigurationProperties(prefix = "weixin")
public class WechatContants {

    //第三方用户唯一凭证
    private String appid;

    //第三方用户唯一凭证密钥，即appsecret
    private String secret;

    //获取access_token填写client_credential
    private String granttype;

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getGranttype() {
        return granttype;
    }

    public void setGranttype(String granttype) {
        this.granttype = granttype;
    }
}
