package com.pghome.mapper.wechat;

import com.pghome.pojo.wxchat.WxUserInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Auther: tianws
 * @Date: 2018/12/28 15:18
 * @Description:
 */
public interface WxUserMapper {

    int insert(WxUserInfo userInfo);

    int updateUserByOpenId(WxUserInfo userInfo);

    List<WxUserInfo> findUserInfo(WxUserInfo userInfo);
}
