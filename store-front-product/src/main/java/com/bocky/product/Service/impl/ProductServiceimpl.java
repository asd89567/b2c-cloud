package com.bocky.product.Service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bocky.client.CategoryClient;
import com.bocky.parm.ProductHotParm;
import com.bocky.parm.ProductIdParm;
import com.bocky.parm.ProductIdsParm;
import com.bocky.pojo.Category;
import com.bocky.pojo.Picture;
import com.bocky.pojo.Product;
import com.bocky.product.Mapper.PictureMapper;
import com.bocky.product.Mapper.ProductMapper;
import com.bocky.product.Service.ProductService;
import com.bocky.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * 　@Author: Bocky
 * 　@Date: 2023-04-22-下午 05:04
 * 　@Description:
 */
@Slf4j
@Service
public class ProductServiceimpl implements ProductService {

    @Resource
    private CategoryClient categoryClient;

    @Resource
    private PictureMapper pictureMapper;
    @Resource
    private ProductMapper productMapper;

    /**
     * 1.使用feign獲取類別名稱獲取數據
     * 2.成功後使用id來查詢
     * 3結果封裝
     * @param categoryName
     * @return
     */
    @Override
    public R promo(String categoryName) {

        R r = categoryClient.getName(categoryName);
        if (r.getCode().equals(R.FAIL_CODE)){
            log.info("類別不存在");
            return r;
        }
        LinkedHashMap<String, Object> map = (LinkedHashMap<String, Object>) r.getData();

        map = (LinkedHashMap<String, Object>) map.get("data");
        Integer categoryId = (Integer) map.get("category_id");

        QueryWrapper<Product> productQueryWrapper = new QueryWrapper<>();

        productQueryWrapper.eq("category_id", categoryId);

        productQueryWrapper.orderByDesc("product_sales");

        IPage<Product> page = new Page<>(1, 7);

        page = productMapper.selectPage(page, productQueryWrapper);

        List<Product> records = page.getRecords();

        log.info("單類別查詢成功");

        return R.ok("查詢成功{}",records);

    }

    @Override
    public R clist() {
        R list = categoryClient.list();
        log.info("查詢成功{}",list);
        return list;
    }

    /**
     * 收索調用全部商品集合
     * @return
     */
    @Override
    public List<Product> alllist() {
        List<Product> products = productMapper.selectList(null);
        log.info("ProductServiceimpl.alllist查詢結果{}",products);
        return products;
    }

    /**
     * 1.調用類別服務
     * 2.類別集合id查詢商品
     * 3結果封裝
     * @param productHotParm
     * @return
     */
    @Override
    public R hots(ProductHotParm productHotParm) {
        R hotscategory = categoryClient.hotscategory(productHotParm);

        if (hotscategory.getCode().equals(R.FAIL_CODE)){
            log.info("錯誤{}",hotscategory.getMsg());
            return hotscategory;
        }
        log.info("查詢成功{}",hotscategory);

        List<Object> data = (List<Object>) hotscategory.getData();

        //商品查詢
        QueryWrapper<Product> productQueryWrapper = new QueryWrapper<>();
        productQueryWrapper.in("category_id",data);
        productQueryWrapper.orderByDesc("product_sales");

        IPage<Product> page = new Page<>(1, 7);

        page = productMapper.selectPage(page, productQueryWrapper);

        List<Product> records = page.getRecords();

        R ok = R.ok("多類別查詢成功", records);
        log.info("查詢成功{}",ok);
        return ok;
    }

    @Override
    public R bycategory(ProductIdsParm productIdsParm) {
        List<Integer> categoryID = productIdsParm.getCategoryID();
        QueryWrapper<Product> productQueryWrapper = new QueryWrapper<>();

        if (!categoryID.isEmpty()) {
            productQueryWrapper.in("category_id", categoryID);
        }
        IPage<Product> page = new Page<>(productIdsParm.getCurrentPage(),productIdsParm.getPageSize() );
        page = productMapper.selectPage(page, productQueryWrapper);

        //結果封裝
        R ok = R.ok("查詢成功", page.getRecords(), page.getTotal());

        log.info("ProductServiceimpl.bycategory業務結束，結果:{}",ok);
        return ok;
    }

    @Override
    public R detail(Integer id) {
        Product product = productMapper.selectById(id);
        R ok = R.ok(product);
        log.info("ProductServiceimpl.detail業務結束，結果:{}",ok);
        return ok;
    }

    @Override
    public R pictures(Integer productID) {
        QueryWrapper<Picture> pictureQueryWrapper = new QueryWrapper<>();
        pictureQueryWrapper.eq("product_id", productID);
        List<Picture> pictures = pictureMapper.selectList(pictureQueryWrapper);
        R ok = R.ok(pictures);
        log.info("ProductServiceimpl.pictures業務結束，結果:{}",ok);
        return ok;
    }

}
