package com.bocky.product.Controller;

import com.bocky.pojo.Product;
import com.bocky.product.Service.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 　@Author: Bocky
 * 　@Date: 2023-04-26-下午 10:51
 * 　@Description: 收索服務調用的controller
 */
@RestController
@RequestMapping("/product")
public class productSearchController {
    @Resource
    private ProductService productService;
    @GetMapping("/list")
    public List<Product> alllist() {
        return productService.alllist();
    }
}
