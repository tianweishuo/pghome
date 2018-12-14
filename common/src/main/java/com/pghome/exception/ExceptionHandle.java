package com.pghome.exception;

import com.pghome.utils.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Auther: tianws
 * @Date: 2018/11/23 15:35
 * @Description: 全局异常处理
 */
@ControllerAdvice
public class ExceptionHandle {

    private static final Logger logger = LoggerFactory.getLogger(ExceptionHandle.class);

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResultUtil handle(Exception e) {
        if (e instanceof PGException) {
            PGException pgException = (PGException) e;
            return ResultUtil.error(pgException.getCode(), pgException.getMessage());
        }else {
            logger.error("【系统异常】{}", e);
            return ResultUtil.error(500, "错误原因:{"+e.getMessage()+"}");
        }
    }


}
