package com.bocky.parm;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 　@Author: Bocky
 * 　@Date: 2023-04-26-上午 12:04
 * 　@Description:
 */
@Data
public class ProductIdParm {
    @NotNull
    private Integer productID;
}
