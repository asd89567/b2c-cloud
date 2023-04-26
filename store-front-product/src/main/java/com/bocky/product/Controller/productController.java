package com.bocky.product.Controller;

import com.bocky.client.CategoryClient;
import com.bocky.parm.ProductHotParm;
import com.bocky.parm.ProductIdParm;
import com.bocky.parm.ProductIdsParm;
import com.bocky.parm.ProductpromoParam;
import com.bocky.product.Mapper.PictureMapper;
import com.bocky.product.Service.ProductService;
import com.bocky.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 　@Author: Bocky
 * 　@Date: 2023-04-22-下午 04:57
 * 　@Description: 商品模塊
 */
@RestController
@Slf4j
@RequestMapping("/product")
public class productController {

    @Autowired
    private ProductService productService;

    @PostMapping("/promo")
    public R promo(@RequestBody @Validated ProductpromoParam productpromoParam, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return R.fail(bindingResult.getFieldError().getDefaultMessage());
        }
        log.info(productpromoParam.getCategoryName());
        return productService.promo(productpromoParam.getCategoryName());
    }
    @PostMapping("/hots")
    public R hots(@RequestBody @Validated ProductHotParm productHotParm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return R.fail(bindingResult.getFieldError().getDefaultMessage());
        }
        return productService.hots(productHotParm);
    }

    @PostMapping("/category/list")
    public R clist() {
        return productService.clist();
    }

    @PostMapping("/bycategory")
    public R bycategory(@RequestBody @Validated ProductIdsParm productIdsParm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return R.fail(bindingResult.getFieldError().getDefaultMessage());
        }
        return productService.bycategory(productIdsParm);
    }

    @PostMapping("/all")
    public R all(@RequestBody @Validated ProductIdsParm productIdsParm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return R.fail(bindingResult.getFieldError().getDefaultMessage());
        }
        return productService.bycategory(productIdsParm);
    }

    @PostMapping("/detail")
    public R detail(@RequestBody @Validated ProductIdParm productIdParm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return R.fail(bindingResult.getFieldError().getDefaultMessage());
        }
        return productService.detail(productIdParm.getProductID());
    }

    @PostMapping("/pictures")
    public R pictures(@RequestBody @Validated ProductIdParm productIdParm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return R.fail(bindingResult.getFieldError().getDefaultMessage());
        }
        return productService.pictures(productIdParm.getProductID());
    }
}
