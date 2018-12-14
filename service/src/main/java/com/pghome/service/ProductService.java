package com.pghome.service;

import com.pghome.dto.CartDTO;
import com.pghome.pojo.product.ProductCategory;
import com.pghome.pojo.product.ProductInfo;

import java.util.List;

/**
 * @Auther: tianws
 * @Date: 2018/11/29 14:28
 * @Description:
 */
public interface ProductService {

    List<ProductCategory> findByCategoryType(List<Integer> type);

    //查询所有上架的商品
    List<ProductInfo> findeUpAll();

    //查询单个
    ProductInfo findOne(String productId);

    //查询全部
    List<ProductInfo> findAll();

    //保存
    boolean saveProductInfo(ProductInfo productInfo);

    //删除
    boolean deleteProductInfo(String productId);

    //加库存
    void increaseStock(List<CartDTO> cartDTOList);

    //减库存
    void decreaseStock(List<CartDTO> cartDTOList);

}
