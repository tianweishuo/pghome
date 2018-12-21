package com.pghome.service;

import com.pghome.param.passenger.CallOrderParam;

/**
 * @Auther: tianws
 * @Date: 2018/12/14 16:24
 * @Description:
 */
public interface PassengerService {

    /**
     * 保存订单
     * @param param
     * @return
     */
    String createCallOrder(CallOrderParam param);


}
