package com.pghome.vo.product;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.pghome.vo.product.ProductInfoVO;
import lombok.Data;

import java.util.List;

/**
 * @Auther: tianws
 * @Date: 2018/11/29 15:13
 * @Description: 商品类目
 */
@Data
public class ProductVO {

    @JsonProperty("name")
    private String categoryName;

    @JsonProperty("type")
    private Integer categoryType;

    @JsonProperty("foods")
    private List<ProductInfoVO> productInfoVOList;

}
