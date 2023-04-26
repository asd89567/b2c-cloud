package com.bocky.category.Service;

import com.bocky.parm.ProductHotParm;
import com.bocky.utils.R;
import org.springframework.stereotype.Service;

/**
 * 　@Author: Bocky
 * 　@Date: 2023-04-22-下午 04:41
 * 　@Description:
 */

public interface CategoryService {

    R list();

    /**
     * 根據類別名稱查詢類別對象
     * @param categoryName
     * @return
     */
    R getByName(String categoryName);

    /**
     * 根據類別傳入的集合返回對應的id集合
     * @param productHotParm
     * @return
     */
    R hotsCategory(ProductHotParm productHotParm);
}
