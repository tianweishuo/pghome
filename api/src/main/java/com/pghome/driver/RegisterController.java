package com.pghome.driver;

import com.pghome.SpringUtil;
import com.pghome.exception.PGException;
import com.pghome.exception.ResultEnum;
import com.pghome.param.driver.RegisterParam;
import com.pghome.pojo.driver.DriverRegister;
import com.pghome.service.DriverService;
import com.pghome.utils.ResultUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.DataInput;
import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: tianws
 * @Date: 2018/12/7 09:32
 * @Description: 司机注册审核
 */
@RestController
@RequestMapping("/driver")
public class RegisterController {

    private static final Logger logger = LoggerFactory.getLogger(RegisterController.class);

    @Autowired
    private DriverService driverService;

    /**
     * 司机注册
     * @param param
     * @return
     */
    @PostMapping("/register")
    public ResultUtil registered(@RequestBody @Valid RegisterParam param, BindingResult bindingResult){
        logger.info("【控制层:司机注册信息】提交信息={}",param);
        //1.判断添加内容不能为空
        if(bindingResult.hasErrors()){
            logger.error("【控制层:司机注册信息】参数不正确,registerParam={}",param);
            throw new PGException(ResultEnum.PARAM_ERROR.getCode(),bindingResult.getFieldError().getDefaultMessage());
        }
        //2.插入数据库
        DriverRegister driverRegister = new DriverRegister();
        param.setCarModel(param.getCarName().toUpperCase());
        BeanUtils.copyProperties(param,driverRegister);
        driverService.create(driverRegister);
        logger.info("【控制层:司机注册信息】");
        return ResultUtil.ok();
    }


    @PostMapping("/login")
    public ResultUtil login(@RequestParam(value = "username") String username,
                            @RequestParam(value = "password") String password,
                            @RequestParam(value = "cid") String cid) {

        if(!StringUtils.isNotBlank(username)){
            throw new PGException(ResultEnum.LOGIN_USERNAME_NOT_NULL);
        }else if(!StringUtils.isNotBlank(password)){
            throw new PGException(ResultEnum.LOGIN_PASSWORD_NOT_NULL);
        }else if(!StringUtils.isNotBlank(cid)){
            throw new PGException(ResultEnum.LOGIN_CID_NOT_NOLL);
        }
        //判断用户是否存在
        DriverRegister driverRegister = driverService.findByPhone(username);
        if(!password.equals(driverRegister.getPassword())){
            throw new PGException(ResultEnum.PASSWORD_ERROR);
        }

        Map map = new HashMap();
        map.put("phone",driverRegister.getPhone());
        map.put("senderId",driverRegister.getDriverId());
        return ResultUtil.ok(map);
    }

    /**
     * 上传身份证
     * @return
     */
    @PostMapping("/")
    public ResultUtil uploadIDCard(){
        return  ResultUtil.ok();
    }

    /**
     * 上传行驶证
     * @return
     */
    public ResultUtil uploadDrivingLicense(){
        return ResultUtil.ok();
    }



}
