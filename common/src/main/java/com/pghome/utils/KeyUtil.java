package com.pghome.utils;

import java.util.Random;

/**
 * @Auther: tianws
 * @Date: 2018/11/30 10:12
 * @Description:
 */
public class KeyUtil {

    /**
     * 生产唯一主键
     * 格式:时间+随机数
     * @return
     */
    public static synchronized String genUniqueKey(){
        Random random = new Random();
        Integer number = random.nextInt(900000)+100000;
        return System.currentTimeMillis()+String.valueOf(number);
    }

}
