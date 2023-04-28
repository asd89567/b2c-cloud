package com.bocky.product.Service;

import com.bocky.parm.ProductHotParm;
import com.bocky.parm.ProductIdParm;
import com.bocky.parm.ProductIdsParm;
import com.bocky.pojo.Product;
import com.bocky.utils.R;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * 　@Author: Bocky
 * 　@Date: 2023-04-22-下午 05:02
 * 　@Description:
 */
public interface ProductService {
    R promo(String categoryName);

    /**
     * 查詢類別集合
     * @return
     */
    R clist();

    /**
     * 多列別查詢
     * @param productHotParm
     * @return
     */
    R hots(ProductHotParm productHotParm);

    /**
     * 通用型業務
     *  傳入id的話，根據id來收巡
     *  沒有傳入類別id，查詢全部
     * @param productIdsParm
     * @return
     */
    R bycategory(ProductIdsParm productIdsParm);

    /**
     * 根據商品Id查詢商品訊息
     * @param id
     * @return
     */
    R detail(Integer id);

    /**
     * 查詢商品的圖片詳情集合
     * @param productID
     * @return
     */
    R pictures(Integer productID);
    /**
     * 收索調用全部商品集合
     * @param productID
     * @return
     */
    List<Product> alllist();
}
