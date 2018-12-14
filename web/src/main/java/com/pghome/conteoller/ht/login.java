package com.pghome.conteoller.ht;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


/**
 * @Auther: tianws
 * @Date: 2018/11/26 10:37
 * @Description: 登录
 */
@Controller
public class login {

    private static final Logger logger = LoggerFactory.getLogger(login.class);

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/index")
    public String index(){
        logger.info("日志打印");
        logger.error("日志打印");
        logger.warn("日志打印");
        logger.debug("日志打印");
        return "index";
    }

    @GetMapping("/index_v1")
    public String index_v1(){
        return "index_v1";
    }

    /**
     * 跳转页面
     * @return
     */
    @GetMapping("/toadvert")
    public String toAdvert(){
        return "/advert/list";
    }
}
