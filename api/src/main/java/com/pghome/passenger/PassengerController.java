package com.pghome.passenger;

import com.pghome.exception.PGException;
import com.pghome.exception.ResultEnum;
import com.pghome.param.passenger.CallOrderParam;
import com.pghome.utils.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @Auther: tianws
 * @Date: 2018/12/14 10:26
 * @Description: 乘客叫车
 */

@RestController
@RequestMapping("/passenger")
public class PassengerController {

    private static final Logger logger = LoggerFactory.getLogger(PassengerController.class);



    /**
     * 叫车
     * @param param
     * @return
     */
    @PostMapping("/call")
    public ResultUtil call(@RequestBody @Valid() CallOrderParam param, BindingResult bindingResult){
        logger.info("【客户开始】");
        //1.参数验证
        if(bindingResult.hasErrors()){
            logger.info("【客户叫车】参数不正确,CallOrderParam={}",param);
            throw new PGException(ResultEnum.PARAM_ERROR.getCode(),bindingResult.getFieldError().getDefaultMessage());
        }
        logger.info(param.toString());
        //2.调用server存储数据


        return ResultUtil.ok();

    }

}
