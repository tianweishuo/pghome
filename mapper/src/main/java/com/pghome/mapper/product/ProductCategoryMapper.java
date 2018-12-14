package com.pghome.mapper.product;

import com.pghome.pojo.product.ProductCategory;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Auther: tianws
 * @Date: 2018/11/29 11:10
 * @Description:
 */
public interface ProductCategoryMapper {

    //根据id查询一个
    ProductCategory findOneById(@Param("id") Integer id);

    //查询全部
    List<ProductCategory> findAll();

    int saveProductCategory(ProductCategory productCategory);

    List<ProductCategory> findByCategoryType(List<Integer> type);

}
