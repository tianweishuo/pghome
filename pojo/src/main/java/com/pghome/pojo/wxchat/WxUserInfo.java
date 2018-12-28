package com.pghome.pojo.wxchat;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * 用户信息对象，不包含 openid 等敏感信息
 */
@Getter
@Setter
@ToString
public class WxUserInfo {
    //主键
    private String openId;
    //用户昵称
    private String nickName;
    //手机号
    private String phoen;
    //用户头像图片的 URL。
    // URL 最后一个数值代表正方形头像大小（有 0、46、64、96、132 数值可选，0 代表 640x640 的正方形头像，
    // 46 表示 46x46 的正方形头像，剩余数值以此类推。默认132），
    // 用户没有头像时该项为空。若用户更换头像，原有头像 URL 将失效。
    private String avatarUrl;
    //用户性别 0-未知,1-男性,2-女性
    private Integer gender;
    //用户所在国家
    private String country;
    //用户所在省份
    private String province;
    //用户所在城市
    private String city;
    //显示 country，province，city 所用的语言 en-英文,zh_CN-简体中文,zh_TW-繁体中文
    private String language;
    //更新时间
    private Date updateTime;
    //创建时间
    private Date createTime;
}
