package com.pghome.conteoller.driver;


import com.pghome.pojo.driver.DriverRegister;
import com.pghome.service.DriverService;
import com.pghome.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Auther: tianws
 * @Date: 2018/12/7 09:32
 * @Description: 司机信息
 */
@RestController
@RequestMapping("/driver")
public class DriverController {

    @Autowired
    private DriverService driverService;

    /**
     * 司机注册信息列表
     * @return
     */
    @GetMapping("/list")
    public ResultUtil list(){
        List<DriverRegister> list = driverService.findAll();
        return ResultUtil.ok(list);
    }


    /**
     * 审核
     * @return
     */
    @PostMapping("/toExamine")
    public ResultUtil toExamine(){
        //1.提交审核
        //2.发送短信
        return ResultUtil.ok();
    }






}
