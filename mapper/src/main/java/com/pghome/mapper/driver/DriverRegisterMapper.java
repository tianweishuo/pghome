package com.pghome.mapper.driver;

import com.pghome.pojo.driver.DriverRegister;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Auther: tianws
 * @Date: 2018/12/7 11:16
 * @Description:
 */
public interface DriverRegisterMapper {

    List<DriverRegister> findAll();

    List<DriverRegister> findByStatus(@Param("status") Integer status);

    int create(DriverRegister param);

    List<DriverRegister> findByDriverRegister(DriverRegister driverRegister);

    DriverRegister findByPhone(@Param("phone") String phone);

    DriverRegister findByCarNumber(@Param("carNumber")String carNumber);
}
