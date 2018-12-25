package com.pghome.handler;


import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.Set;


/**
 * 请求的拦截器
 */
public class RequestInterceptor implements HandlerInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(RequestInterceptor.class);

    /**
     * 在请求处理之前进行调用（Controller方法调用之前
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        logger.info("【请求地址:】:{}",request.getRequestURL());
        logger.info("【请求参数:】:{}", JSON.toJSONString(request.getParameterMap()));
        Map<String, String[]> parameterMap = request.getParameterMap();
        Set<String> paramKey = parameterMap.keySet();
        for(String key:paramKey){
            String[] value = parameterMap.get(key);
            StringBuffer valu = new StringBuffer();
            int count = 0;
            for(String val:value){
                count++;
                if(count == value.length){
                    valu.append(val);
                }else{
                    valu.append(val+",");
                }

            }
            logger.info("【请求的Key】key={},values={}",key,valu);
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
