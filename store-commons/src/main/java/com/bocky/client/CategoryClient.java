package com.bocky.client;

import com.bocky.parm.ProductHotParm;
import com.bocky.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * 　@Author: Bocky
 * 　@Date: 2023-04-22-下午 04:53
 * 　@Description:
 */
@FeignClient(value= "category-service")
public interface CategoryClient {
    @GetMapping("category/promo/{categoryName}")
    R getName(@PathVariable("categoryName") String categoryName);

    @PostMapping("/category/hots")
    R hotscategory(@RequestBody @Validated ProductHotParm productHotParm);

    @GetMapping("/category/list")
    R list();
}
