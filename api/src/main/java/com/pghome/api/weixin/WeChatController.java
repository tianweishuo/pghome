package com.pghome.api.weixin;

import com.alibaba.fastjson.JSON;
import com.pghome.SpringUtil;
import com.pghome.constant.WechatApiUrlConstants;
import com.pghome.constant.WechatContants;
import com.pghome.param.wechat.WxUserInfoParam;
import com.pghome.pojo.wx.WeChatCode2Session;
import com.pghome.pojo.wxchat.WxUserInfo;
import com.pghome.service.WxUserService;
import com.pghome.utils.HttpUtils;
import com.pghome.utils.ResultUtil;
import com.pghome.utils.ValidationUtil;
import me.chanjar.weixin.mp.api.WxMpService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: tianws
 * @Date: 2018/12/3 15:33
 * @Description:
 */
@RestController
@RequestMapping("/wechat")
public class WeChatController {

    private static final Logger logger = LoggerFactory.getLogger(WeChatController.class);

    @Autowired
    private WechatContants wechatContants;

    @Autowired
    private WxMpService wxMpService;

    @Autowired
    private WxUserService wxUserService;




    /**
     * 登陆发送code
     * @return
     */
    @GetMapping("/openid")
    public ResultUtil openid(@RequestParam("code") String code){
        WxUserService wxUserService1 = (WxUserService)SpringUtil.getBean("wxUserServiceImpl");
        WxUserService wxUserService2 = (WxUserService)SpringUtil.getBean("wxUserServiceImpl");
        Map<String,Object> param = new HashMap();
        param.put("appid",wechatContants.getAppid());
        param.put("secret",wechatContants.getSecret());
        param.put("js_code",code);
        param.put("grant_type",wechatContants.getGranttype());
        String respone = HttpUtils.doGet(WechatApiUrlConstants.CODE2_SESSION, param);
        logger.info("【获取凭证校验接口】返回报文:{}",respone);
        WeChatCode2Session code2Session = JSON.parseObject(respone, WeChatCode2Session.class);
        return ResultUtil.ok(code2Session);
    }

    /**
     * 新增用户
     * @return
     */
    @PostMapping("/add")
    public ResultUtil add(@RequestBody WxUserInfoParam param){
        logger.info("[控制层:微信用户授权信息获取]:开始");
        logger.info("[控制层:微信用户授权信息获取]:userinfo=={}",param);
        wxUserService.insert(param);
        logger.info("[控制层:微信用户授权信息获取]:结束");
        return ResultUtil.ok();
    }


    /**
     * 获取用户信息
     * @param openid
     * @return
     */
    @GetMapping("/userInfo")
    public ResultUtil userInfo(@RequestParam("openid") String openid){
        WxUserInfo userInfo = wxUserService.findUserInfoByOpenid(openid);
        return ResultUtil.ok(userInfo);
    }

    /**
     * 检测手机号是否存在
     * @param openId
     * @return
     */
    @GetMapping("/checkPhone")
    public ResultUtil checkPhone(@RequestParam("openId") String openId){
        WxUserInfo userinfo = wxUserService.findUserinfoByPhone(openId);
        return ResultUtil.ok(userinfo);
    }


    /**
     * 完善手机号
     * @return
     */
    @PostMapping("/registerPhone")
    public ResultUtil registerPhone(@RequestParam("openId") String openId,@RequestParam("phone")String phone){
        wxUserService.updatePhoneByOpenId(openId,phone);
        return ResultUtil.ok();
    }


    /**
     * 微信加密签名
     *
     * @param signature 微信加密签名
     * @param timestamp 时间戮
     * @param nonce     随机数
     * @param echostr   随机字符串
     */
    @GetMapping("/access")
    public void access(HttpServletRequest request, HttpServletResponse response, String signature, String timestamp, String nonce, String echostr) throws IOException {

        PrintWriter out = response.getWriter();
        //验证请求确认成功原样返回echostr参数内容，则接入生效，成为开发者成功，否则失败
        if(ValidationUtil.checkSignature(signature, timestamp, nonce)){
            out.print(echostr);
        }
        out.print(echostr);
        out.flush();
        out.close();
    }



    @GetMapping("/authorize")
    public void authorize(@RequestParam("returnUrl") String returnUrl) {
        logger.info("【微信授信】{}", returnUrl);

    }


}
