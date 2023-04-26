package com.bocky.parm;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 　@Author: Bocky
 * 　@Date: 2023-04-22-下午 04:29
 * 　@Description: 類別熱門
 */
@Data
public class ProductpromoParam {

    @NotNull
    private String categoryName;
}
