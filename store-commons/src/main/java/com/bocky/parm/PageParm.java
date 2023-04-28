package com.bocky.parm;

import lombok.Data;

/**
 * 　@Author: Bocky
 * 　@Date: 2023-04-27-下午 06:45
 * 　@Description:
 */
@Data
public class PageParm {

    private int currentPage = 1;
    private int pageSize = 15;
}
