package com.pghome.service.impl;

import com.fasterxml.jackson.databind.util.BeanUtil;
import com.pghome.exception.PGException;
import com.pghome.exception.ResultEnum;
import com.pghome.idworker.Sid;
import com.pghome.mapper.wechat.WxUserMapper;
import com.pghome.param.wechat.WxUserInfoParam;
import com.pghome.pojo.wxchat.WxUserInfo;
import com.pghome.service.WxUserService;
import com.pghome.utils.CopyUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;

import java.util.Date;
import java.util.List;

/**
 * @Auther: tianws
 * @Date: 2018/12/28 15:07
 * @Description:
 */
@Slf4j
@Service
public class WxUserServiceImpl implements WxUserService {

    @Autowired
    private Sid sid;

    @Autowired
    private WxUserMapper wxUserMapper;


    /**
     * 添加微信用户信息
     * @param param
     * @return
     */
    @Override
    public void insert(WxUserInfoParam param) {
        log.info("[新增微信用户]:开始");
        log.info("[微信用户信息]:param=={}",param);
        WxUserInfo user = new WxUserInfo();
        user.setOpenId(param.getOpenId());
        List<WxUserInfo> list = wxUserMapper.findUserInfo(user);
        if(list == null || list.size() == 0){
            WxUserInfo wxUserInfo = new WxUserInfo();
            CopyUtils.copyProperties(param,wxUserInfo);
            wxUserInfo.setUpdateTime(new Date());
            wxUserInfo.setCreateTime(new Date());
            try{
                StopWatch watch = new StopWatch();
                log.info("[信息]:userinfo==",wxUserInfo);
                log.info("[插入信息]:开始");
                watch.start();
                wxUserMapper.insert(wxUserInfo);
                watch.stop();
                log.info("[插入信息]:结束,耗时间=={}ms",watch.getTotalTimeMillis());
            }catch (Exception e){
                log.info("[新增微信用户]:失败=={}",e.getMessage());
                throw new PGException(ResultEnum.INSERT_ERROR);
            }
            log.info("[新增微信用户]:结束");
        }else {
            try{
                //使用对象拷贝工具,将查询出的数据进行覆盖
                BeanUtils.copyProperties(param,list.get(0));
                user.setUpdateTime(new Date());
                log.info("[更新信息]:数据==={}",list.get(0));
                log.info("[更新信息]:开始");
                StopWatch watch = new StopWatch();
                watch.start();
                wxUserMapper.updateUserByOpenId(list.get(0));
                watch.stop();
                log.info("[更新信息]:结束,耗时间=={}ms",watch.getTotalTimeMillis());
            }catch (Exception e){
                log.info("[更新微信用户]:失败=={}",e.getMessage());
                throw new PGException(ResultEnum.UPDATE_ERROR);
            }

        }
    }

    /**
     * 通过手机号查询用户
     * @param phone
     * @return
     */
    @Override
    public WxUserInfo findUserinfoByPhone(String openId) {
        WxUserInfo userInfo = new WxUserInfo();
        userInfo.setOpenId(openId);
        List<WxUserInfo> list = null;
        try {
            list = wxUserMapper.findUserInfo(userInfo);
            if(list == null && list.size() < 0){
                throw new PGException(ResultEnum.SELECT_ERROR);
            }
            WxUserInfo wxUserInfo = list.get(0);
            if (wxUserInfo.getPhoen() == null){
                throw new PGException(ResultEnum.SELECT_ERROR);
            }
            BeanUtils.copyProperties(wxUserInfo,userInfo);
        }catch (Exception e){
            log.info("[通过手机号查询]:失败=={}",e.getMessage());
            throw new PGException(ResultEnum.SELECT_ERROR);
        }
        return userInfo;
    }
}
