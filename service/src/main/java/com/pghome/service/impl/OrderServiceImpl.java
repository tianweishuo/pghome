package com.pghome.service.impl;

import com.pghome.dto.CartDTO;
import com.pghome.dto.order.OrderDTO;
import com.pghome.enums.order.OrderStatusEnum;
import com.pghome.enums.order.PayStatusEnum;
import com.pghome.exception.ResultEnum;
import com.pghome.exception.PGException;
import com.pghome.mapper.order.OrderDetailMapper;
import com.pghome.mapper.order.OrderMasterMapper;
import com.pghome.mapper.product.ProductInfoMapper;
import com.pghome.pojo.order.OrderDetail;
import com.pghome.pojo.order.OrderMaster;
import com.pghome.pojo.product.ProductInfo;
import com.pghome.service.OrderService;
import com.pghome.service.ProductService;
import com.pghome.utils.CopyUtils;
import com.pghome.utils.KeyUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Auther: tianws
 * @Date: 2018/11/29 17:29
 * @Description:
 */
@Service
public class OrderServiceImpl implements OrderService {

    private static final Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);

    @Autowired
    private OrderMasterMapper orderMasterMapper;

    @Autowired
    private OrderDetailMapper orderDetailMapper;

    @Autowired
    private ProductInfoMapper productInfoMapper;

    @Autowired
    private ProductService productService;

    /**
     * 创建订单
     * @param orderDTO
     * @return
     */
    @Transactional
    @Override
    public OrderDTO create(OrderDTO orderDTO) {
        String orderId = KeyUtil.genUniqueKey();
        BigDecimal orderAmont = new BigDecimal(BigInteger.ZERO);
        //1.查询商品(数量,价格)
        for(OrderDetail orderDetail:orderDTO.getOrderDetailList()){
            ProductInfo productInfo = productInfoMapper.findOne(orderDetail.getProductId());
            if(productInfo == null){
                throw new PGException(ResultEnum.PRODUCT_NOT_EXISTS);
            }
            //2.计算订单总价
            orderAmont = productInfo.getProductPrice().multiply(new BigDecimal(orderDetail.getProductQuantity())).add(orderAmont);
            //订单详情入库
            orderDetail.setDetailId(KeyUtil.genUniqueKey());
            orderDetail.setOrderId(orderId);
            CopyUtils.copyProperties(productInfo,orderDetail);
            orderDetail.setCreateTime(new Date());
            orderDetail.setUpdateTime(new Date());
            orderDetailMapper.saveOrderDetail(orderDetail);
        }
        //3.写入订单数据库（ordermaster）
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setOrderId(orderId);
        orderMaster.setOrderAmount(orderAmont);
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
        orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());
        orderMaster.setCreateTime(new Date());
        orderMaster.setUpdateTime(new Date());
        CopyUtils.copyProperties(orderDTO,orderMaster);
        orderMasterMapper.saveOrderMaster(orderMaster);
        //4.扣库存
        List<CartDTO> cartDTOList = orderDTO.getOrderDetailList().stream().map(e ->
                new CartDTO(e.getProductId(), e.getProductQuantity())
        ).collect(Collectors.toList());
        productService.decreaseStock(cartDTOList);
        return orderDTO;
    }

    /**
     * 查询单个订单
     * @param orderId
     * @return
     */
    @Override
    public OrderDTO findOne(String orderId) {
        OrderMaster orderMaster = orderMasterMapper.findOne(orderId);
        //判断订单是否存在
        if(orderMaster == null){
            throw new PGException(ResultEnum.ORDER_NOT_EXISTS);
        }
        //查询订单详情
        List<OrderDetail> orderDetailList = orderDetailMapper.findByOrderId(orderId);
        if(CollectionUtils.isEmpty(orderDetailList)){
            throw new PGException(ResultEnum.ORDERDETAIL_NOT_EXISTS);
        }
        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(orderMaster,orderDTO);
        orderDTO.setOrderDetailList(orderDetailList);
        return orderDTO;
    }

    /**
     * 查询订单列表
     * @param buyerOpenid
     * @return
     */
    @Override
    public List<OrderDTO> findList(String buyerOpenid) {
        List<OrderMaster> orderMasters = orderMasterMapper.findByBuyerOpenid(buyerOpenid);
        List<OrderDTO> list = new ArrayList<>();
        for(OrderMaster orderMaster:orderMasters){
            OrderDTO orderDTO = new OrderDTO();
            BeanUtils.copyProperties(orderMasters,orderDTO);
            list.add(orderDTO);
        }
        return list;
    }

    /**
     * 取消订单
     * @param orderDTO
     * @return
     */
    @Override
    @Transactional
    public OrderDTO cancel(OrderDTO orderDTO) {
        OrderMaster orderMaster = new OrderMaster();
        if(orderDTO == null){
            throw new PGException(ResultEnum.ORDER_NOT_EXISTS);
        }
        //判断订单状态,新下订单才能取消
        if(orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())){
            logger.error("【取消订单】订单状态不正确,orderId={},orderStatus={}", orderDTO.getOrderId(), orderDTO.getOrderStatus());
            throw new PGException(ResultEnum.ORDER_STATUS_ERROR);
        }
        //修改订单状态
        int update = orderMasterMapper.updateOrderStatus(orderMaster.getOrderId(), OrderStatusEnum.CAHCEL.getCode());
        if(update <= 0){
            logger.error("【取消订单】更新失败,orderId={}",orderMaster.getOrderId());
            throw new PGException(ResultEnum.ORDER_UPDATE_FAIL);
        }
        //返还库存
        if(CollectionUtils.isEmpty(orderDTO.getOrderDetailList())){
            logger.error("【取消订单】订单中商品详情,orderId={}",orderMaster.getOrderId());
            throw new PGException(ResultEnum.ORDER_DETAIL_EMPTY);
        }
        List<CartDTO> cartDTOList = orderDTO.getOrderDetailList().stream()
                .map(e -> new CartDTO(e.getProductId(),e.getProductQuantity()))
                .collect(Collectors.toList());
        productService.increaseStock(cartDTOList);
        //如果已支付,需要退款
        if(orderMaster.getPayStatus().equals(PayStatusEnum.SUCESS.getCode())){
            //TODO
        }
        return new OrderDTO();
    }

    /**
     * 完结订单
     * @param orderDTO
     * @return
     */
    @Override
    @Transactional
    public OrderDTO finish(OrderDTO orderDTO) {
        //判断订单状态
        if(!orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())){
            logger.error("【完结订单】订单状态不正确,orderId={},orderStatus={}",orderDTO.getOrderId(),orderDTO.getOrderStatus());
            throw new PGException(ResultEnum.ORDER_STATUS_ERROR);
        }
        //修改订单状态
        orderDTO.setOrderStatus(OrderStatusEnum.FINISHED.getCode());
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDTO,orderMaster);
        int update = orderMasterMapper.updateOrderStatus(orderMaster.getOrderId(), OrderStatusEnum.CAHCEL.getCode());
        if(update <= 0){
            logger.error("【完结订单】更新失败,orderMaset={}",orderMaster);
            throw new PGException(ResultEnum.ORDER_UPDATE_FAIL);
        }
        return orderDTO;
    }

    /**
     * 支付订单
     * @param orderDTO
     * @return
     */
    @Override
    @Transactional
    public OrderDTO paid(OrderDTO orderDTO) {
        //判断订单状态
        if(orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())){
            logger.error("【支付订单】订单状态不正确,orderId={},orderStatus={}",orderDTO.getOrderId(),orderDTO.getOrderStatus());
            throw new PGException(ResultEnum.ORDER_STATUS_ERROR);
        }
        //判断支付状态
        if(!orderDTO.getPayStatus().equals(PayStatusEnum.WAIT.getCode())){
            logger.error("【订单支付完成】订单支付状态不正确,orderDTO={}",orderDTO);
            throw new PGException(ResultEnum.ORDER_PAY_STATUS_ERROR);
        }
        //修改支付状态
        orderDTO.setPayStatus(PayStatusEnum.SUCESS.getCode());
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDTO,orderMaster);
        int update = orderMasterMapper.updatePayStatus(orderMaster.getOrderId(), OrderStatusEnum.CAHCEL.getCode());
        if(update <= 0){
            logger.error("【订单支付完成】更新失败,orderMaset={}",orderMaster);
            throw new PGException(ResultEnum.ORDER_UPDATE_FAIL);
        }
        return orderDTO;
    }
}
