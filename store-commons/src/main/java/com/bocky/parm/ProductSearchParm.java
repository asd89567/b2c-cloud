package com.bocky.parm;

import lombok.Data;

/**
 * 　@Author: Bocky
 * 　@Date: 2023-04-27-下午 06:43
 * 　@Description:搜索關鍵字和分頁集合
 */
@Data
public class ProductSearchParm extends PageParm{
    private String search;

    /**
     * 運算分頁起始值
     * @return
     */
    public Integer getForm(){
        return (getCurrentPage()-1)*getPageSize();
    }

}
