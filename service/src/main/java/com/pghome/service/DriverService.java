package com.pghome.service;

import com.pghome.pojo.driver.DriverRegister;

import java.util.List;

/**
 * @Auther: tianws
 * @Date: 2018/12/7 13:33
 * @Description:
 */
public interface DriverService {

    /**
     * 查询全部
     * @return
     */
    List<DriverRegister> findAll();

    /**
     * 根据状态查询
     * @param status
     * @return
     */
    List<DriverRegister> findByStatus(Integer status);

    /**
     * 创建
     * @param param
     */
    void create(DriverRegister param);
}
