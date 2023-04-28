package com.bocky.search.contrroller;

import com.bocky.parm.ProductSearchParm;
import com.bocky.search.Service.SearchService;
import com.bocky.utils.R;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 　@Author: Bocky
 * 　@Date: 2023-04-27-下午 06:48
 * 　@Description:
 */
@RestController
@RequestMapping("/search")
public class SearchController {

    @Resource
    private SearchService searchService;
    @PostMapping("/product")
    public R search(@RequestBody ProductSearchParm productSearchParm) throws JsonProcessingException {

        return searchService.search(productSearchParm);
    }
}
