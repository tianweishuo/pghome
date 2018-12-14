package com.pghome.conteoller.wx;

import com.alibaba.fastjson.JSON;
import com.pghome.constant.WeChatTokenRestContants;
import com.pghome.constant.WechatApiUrlConstants;
import com.pghome.exception.PGException;
import com.pghome.exception.ResultEnum;
import com.pghome.pojo.wx.BasicButton;
import com.pghome.pojo.wx.Menu;
import com.pghome.pojo.wx.ViewButton;
import com.pghome.utils.HttpUtils;
import com.pghome.utils.RedisOperator;
import com.pghome.utils.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: tianws
 * @Date: 2018/11/23 14:30
 * @Description: 微信菜单
 */
@RestController
@RequestMapping("/button")
public class WeChatMenuController {

    private static final Logger logger = LoggerFactory.getLogger(WeChatMenuController.class);

    @Autowired
    private RedisOperator redisOperator;

    /**
     * 按钮发布
     */
    @PostMapping("/push")
    public ResultUtil push(){
        String token = redisOperator.get("weixin_token");
        token = token.substring(1,token.length()-1);
        token = token.replace("\\", "");
        if(token == null){
            throw new PGException(ResultEnum.WEIXIN_TOKEN_NULL);
        }
        logger.info("【发布自定义节点】获取reids中获取token:{}",token);
        WeChatTokenRestContants response = JSON.parseObject(token, WeChatTokenRestContants.class);
        Menu menu = new Menu();
        ViewButton viewButton = new ViewButton();
        viewButton.setName("你好");
        viewButton.setUrl("wwww.baidu.com");
        menu.setButton(new BasicButton[]{viewButton});
        StringBuffer url = new StringBuffer();
        url.append(WechatApiUrlConstants.CREATE_BUTTON);
        url.append("?access_token=");
        url.append(response.getAccess_token());
        String param = JSON.toJSONString(menu);
        String result = HttpUtils.doPost(url.toString(),param);
        WeChatTokenRestContants restContants = JSON.parseObject(result, WeChatTokenRestContants.class);
        if("40018".equals(restContants.getErrcode())){
            /*throw new PGException("40018","无效菜单名长度");*/
            throw new PGException(ResultEnum.WEIXIN_TOKEN_NULL);
        } else if("0".equals(restContants.getErrcode())){
            return ResultUtil.ok();
        }else {
            /*throw new PGException(restContants.getErrcode(),restContants.getErrmsg());*/
            throw new PGException(ResultEnum.WEIXIN_TOKEN_NULL);
        }
    }



}
