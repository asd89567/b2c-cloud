package com.bocky.doc;

import com.bocky.pojo.Product;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 　@Author: Bocky
 * 　@Date: 2023-04-26-下午 11:37
 * 　@Description:
 */
@Data
@NoArgsConstructor
public class ProductDoc extends Product {

    /**
     * 用于模糊查询字段,由商品名,标题和描述组成
     */
    private String all;

    public ProductDoc(Product product) {
        super(product.getProductId(),product.getProductName(),
                product.getCategoryId(),product.getProductTitle(),
                product.getProductIntro(),product.getProductPicture(),
                product.getProductPrice(),product.getProductSellingPrice(),
                product.getProductNum(),product.getProductSales());
        this.all = product.getProductName()+product.getProductTitle()+product.getProductIntro();
    }
}

