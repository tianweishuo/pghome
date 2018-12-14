package com.pghome.service.impl;

import com.pghome.exception.PGException;
import com.pghome.exception.ResultEnum;
import com.pghome.mapper.driver.DriverRegisterMapper;
import com.pghome.pojo.driver.DriverRegister;
import com.pghome.service.DriverService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @Auther: tianws
 * @Date: 2018/12/7 13:33
 * @Description:
 */
@Service
public class DriverServiceImpl implements DriverService {

    private static final Logger logger = LoggerFactory.getLogger(DriverServiceImpl.class);

    @Autowired
    private DriverRegisterMapper driverRegisterMapper;


    /**
     * 查询全部
     * @return 全部信息
     */
    @Override
    public List<DriverRegister> findAll() {
        return driverRegisterMapper.findAll();
    }

    /**
     * 根据状态查询
     * @param status 审核状态
     * @see com.pghome.enums.driver.StatusEnum 审核状态
     * @return 全部信息
     */
    @Override
    public List<DriverRegister> findByStatus(Integer status) {
        return driverRegisterMapper.findByStatus(status);
    }

    /**
     * 司机注册
     * @param param 审核信息
     */
    @Override
    @Transactional
    public void create(DriverRegister param) {
        param.setCreateTime(new Date());
        param.setUpdateTime(new Date());
        //1.根据手机号、车牌号判断信息是否重复
        DriverRegister phoneInfo = driverRegisterMapper.findByPhone(param.getPhone());
        DriverRegister CarNumberInfo = driverRegisterMapper.findByCarNumber(param.getCarNumber());
        if(phoneInfo != null){
            //手机号重复
            logger.warn("【司机注册】注册手机号已存在:{}",param.getPhone());
            throw new PGException(ResultEnum.DRIVER_REGISTER_PHONE_EXISTS);
        }
        if(CarNumberInfo != null){
            //车牌号重复
            logger.warn("【司机注册】注册车牌号已存在:{}",param.getCarNumber());
            throw new PGException(ResultEnum.DRIVER_REGISTER_CARNUMBER_EXISTS);
        }
        driverRegisterMapper.create(param);
    }

}
