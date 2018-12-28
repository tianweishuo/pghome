package com.pghome.service;

import com.pghome.param.wechat.WxUserInfoParam;
import com.pghome.pojo.wxchat.WxUserInfo;

/**
 * @Auther: tianws
 * @Date: 2018/12/28 15:07
 * @Description:
 */
public interface WxUserService {

    /**
     * 添加微信用户
     * @param param
     * @return
     */
    void insert(WxUserInfoParam param);

    /**
     * 通过手机号查询用户
     * @param openId
     * @return
     */
    WxUserInfo findUserinfoByPhone(String openId);

}
