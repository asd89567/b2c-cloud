package com.bocky.parm;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 　@Author: Bocky
 * 　@Date: 2023-04-25-下午 04:40
 * 　@Description:
 */
@Data
public class ProductHotParm {

    @NotNull
    private List<String> categoryName;
}
