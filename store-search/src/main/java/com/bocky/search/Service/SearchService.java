package com.bocky.search.Service;

import com.bocky.parm.ProductSearchParm;
import com.bocky.utils.R;
import com.fasterxml.jackson.core.JsonProcessingException;

/**
 * 　@Author: Bocky
 * 　@Date: 2023-04-28-下午 01:44
 * 　@Description:
 */
public interface SearchService {
    R search(ProductSearchParm productSearchParm) throws JsonProcessingException;
}
