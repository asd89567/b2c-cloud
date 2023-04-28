package com.bocky.parm;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 　@Author: Bocky
 * 　@Date: 2023-04-25-下午 11:40
 * 　@Description:
 */
@Data
public class ProductIdsParm extends PageParm{
    @NotNull
    private List<Integer> categoryID;

}
