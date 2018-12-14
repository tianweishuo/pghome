package com.pghome.service.impl;

import com.pghome.dto.CartDTO;
import com.pghome.exception.PGException;
import com.pghome.exception.ResultEnum;
import com.pghome.mapper.product.ProductCategoryMapper;
import com.pghome.mapper.product.ProductInfoMapper;
import com.pghome.pojo.product.ProductCategory;
import com.pghome.pojo.product.ProductInfo;
import com.pghome.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @Auther: tianws
 * @Date: 2018/11/29 14:28
 * @Description:
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductInfoMapper productInfoMapper;

    @Autowired
    private ProductCategoryMapper productCategoryMapper;


    @Override
    public List<ProductCategory> findByCategoryType(List<Integer> type) {
        return productCategoryMapper.findByCategoryType(type);
    }

    /**
     * 查询所有上架的商品
     * @return
     */
    @Override
    public List<ProductInfo> findeUpAll() {
        return productInfoMapper.findUpAll();
    }

    @Override
    public ProductInfo findOne(String productId) {
        return productInfoMapper.findOne(productId);
    }

    @Override
    public List<ProductInfo> findAll() {
        return productInfoMapper.findAll();
    }

    @Transactional
    @Override
    public boolean saveProductInfo(ProductInfo productInfo) {
        boolean flag = false;
        int count = productInfoMapper.saveProductInfo(productInfo);
        if(count > 0){
            flag = true;
        }
        return flag;
    }

    @Override
    public boolean deleteProductInfo(String productId) {
        boolean flag = false;
        int count = productInfoMapper.deleteProductInfo(productId);
        if(count > 0){
            flag = true;
        }
        return flag;
    }

    //增库存
    @Override
    @Transactional
    public void increaseStock(List<CartDTO> cartDTOList) {
        for(CartDTO cartDTO:cartDTOList){
            ProductInfo productInfo = productInfoMapper.findOne(cartDTO.getProductId());
            if(productInfo == null){
                throw new PGException(ResultEnum.PRODUCT_NOT_EXISTS);
            }
           Integer result =  productInfo.getProductStock() + cartDTO.getProductQuantity();
            productInfo.setProductStock(result);
            productInfo.setUpdateTime(new Date());
            productInfoMapper.updateProductInfo(productInfo);
        }
    }

    //减库存
    @Transactional
    @Override
    public void decreaseStock(List<CartDTO> cartDTOList) {
        //TODO 超卖优化
        for(CartDTO cartDTO:cartDTOList){
            ProductInfo productInfo = productInfoMapper.findOne(cartDTO.getProductId());
            if(productInfo == null){
                throw new PGException(ResultEnum.PRODUCT_NOT_EXISTS);
            }
            Integer result =  productInfo.getProductStock() - cartDTO.getProductQuantity();
            if(result < 0){
                throw new PGException(ResultEnum.PRODUCT_STOCK_ERROR);
            }
            productInfo.setProductStock(result);
            productInfo.setUpdateTime(new Date());
            productInfoMapper.updateProductInfo(productInfo);
        }
    }
}
