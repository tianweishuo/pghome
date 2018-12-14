package com.pghome.mapper.product;

import com.pghome.pojo.product.ProductInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Auther: tianws
 * @Date: 2018/11/29 14:11
 * @Description:
 */
public interface ProductInfoMapper {

    List<ProductInfo> findByProductStatus(@Param("productStatus") Integer productStatus);

    int saveProductInfo(ProductInfo productInfo);

    int deleteProductInfo(@Param("productId") String productId);

    ProductInfo findOne(@Param("productId") String productId);

    int updateProductInfo(ProductInfo productInfo);

    List<ProductInfo> findAll();

    List<ProductInfo> findUpAll();
}
