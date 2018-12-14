package com.pghome;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Auther: tianws
 * @Date: 2018/11/22 13:57
 * @Description:
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.pghome"})
//将项目中对应的mapper类的路径加进来就可以了
@MapperScan(basePackages = "com.pghome.mapper")
public class WebApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebApplication.class);
    }

}
