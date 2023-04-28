package com.bocky.client;

import com.bocky.pojo.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * 　@Author: Bocky
 * 　@Date: 2023-04-26-下午 10:57
 * 　@Description:商品服務客戶端
 */
@FeignClient(value = "product-service")
public interface ProductClient {
    /**
     * 收索服務調用，全部數據查詢，用於同步es數據
     * @return
     */
    @GetMapping("/product/list")
    public List<Product> alllist();
}
