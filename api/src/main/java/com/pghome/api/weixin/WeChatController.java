package com.pghome.api.weixin;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.pghome.constant.WechatApiUrlConstants;
import com.pghome.constant.WechatContants;
import com.pghome.pojo.wx.WeChatCode2Session;
import com.pghome.pojo.wx.WeChatUserInfo;
import com.pghome.utils.HttpUtils;
import com.pghome.utils.ResultUtil;
import com.pghome.utils.ValidationUtil;
import me.chanjar.weixin.mp.api.WxMpService;
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




    /**
     * 登陆发送code
     * @return
     */
    @GetMapping("/openid")
    public ResultUtil openid(@RequestParam("code") String code){
        /*?appid=APPID&secret=SECRET&js_code=JSCODE&grant_type=authorization_code*/
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
     * 接受用户信息
     * @return
     */
    @PostMapping("/userinfo")
    public ResultUtil userinfo(WeChatUserInfo userInfo){
        logger.info(userInfo.toString());
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
