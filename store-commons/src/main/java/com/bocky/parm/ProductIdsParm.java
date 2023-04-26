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
public class ProductIdsParm {
    @NotNull
    private List<Integer> categoryID;

    private Integer currentPage = 1;

    private Integer pageSize = 15; //默認值
}
