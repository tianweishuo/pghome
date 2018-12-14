package com.pghome.api.buyer;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.pghome.dto.order.OrderDTO;
import com.pghome.exception.PGException;
import com.pghome.exception.ResultEnum;
import com.pghome.from.OrderForm;
import com.pghome.pojo.order.OrderDetail;
import com.pghome.service.BuyerService;
import com.pghome.service.OrderService;
import com.pghome.utils.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: tianws
 * @Date: 2018/12/3 13:30
 * @Description:
 */
@RestController
@RequestMapping("/buyer/order")
public class BuyerOrderController {
    private static final Logger logger = LoggerFactory.getLogger(BuyerOrderController.class);

    @Autowired
    private OrderService orderService;

    @Autowired
    private BuyerService buyerService;


    //创建订单
    @PostMapping("/create")
    public ResultUtil create(@Valid OrderForm orderFrom, BindingResult bindingResult){
        logger.info("【创建订单】创建订单开始");
        if(bindingResult.hasErrors()){
            logger.error("【创建订单】参数不正确,orderForm={}",orderFrom);
            throw new PGException(ResultEnum.PARAM_ERROR.getCode(),bindingResult.getFieldError().getDefaultMessage());
        }
        OrderDTO orderDTO = this.convert(orderFrom);
        if(CollectionUtils.isEmpty(orderDTO.getOrderDetailList())){
            logger.error("【创建订单】购物车不能为空");
            throw new PGException(ResultEnum.CART_EMPTY);
        }
        OrderDTO createResult = orderService.create(orderDTO);
        return ResultUtil.ok(createResult);
    }

    //订单列表
    public ResultUtil list(@RequestParam("openid")String openid,
                           @RequestParam(value = "page" ,defaultValue = "0")Integer page,
                           @RequestParam(value = "sizi",defaultValue = "10")Integer size){
        if(StringUtils.isEmpty(openid)){
            logger.error("【查询订单列表】openid为空");
            throw new PGException(ResultEnum.PARAM_ERROR);
        }
        return ResultUtil.ok();
    }

    //订单详情
    @GetMapping("/detail")
    public ResultUtil detail(@RequestParam("openid")String openid,
                             @RequestParam("orderId")String orderId){
        OrderDTO orderDTO = buyerService.findOrderOne(openid, orderId);
        return ResultUtil.ok(orderDTO);
    }

    //取消订单
    @PostMapping("/cancel")
    public ResultUtil cancel(@RequestParam("openid")String openid,
                             @RequestParam("orderId")String orderId){
        buyerService.findOrderOne(openid, orderId);

        RestTemplate restTemplate = new RestTemplate();

        return ResultUtil.ok();
    }


    //转换
    private OrderDTO convert(OrderForm orderForm){
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setBuyerName(orderForm.getName());
        orderDTO.setBuyerPhone(orderForm.getPhone());
        orderDTO.setBuyerAddress(orderForm.getAddress());
        orderDTO.setBuyerOpenid(orderForm.getOpenid());
        List<OrderDetail> orderDetails = new ArrayList<>();
        try {
            orderDetails = JSONArray.parseArray(orderForm.getItems(), OrderDetail.class);
        }catch (Exception e){
            logger.error("【对象转换】错误,String={}",orderForm.getItems());
            throw new PGException(ResultEnum.PARAM_ERROR);
        }
        orderDTO.setOrderDetailList(orderDetails);
        return orderDTO;
    }

}
