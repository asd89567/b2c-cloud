package com.bocky.category.Service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bocky.category.Mapper.CategoryMapper;
import com.bocky.category.Service.CategoryService;
import com.bocky.parm.ProductHotParm;
import com.bocky.pojo.Category;
import com.bocky.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

import static jdk.nashorn.internal.runtime.regexp.joni.Config.log;

/**
 * 　@Author: Bocky
 * 　@Date: 2023-04-22-下午 04:43
 * 　@Description:
 */
@Slf4j
@Service
public class CategoryServiceimpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public R list() {
        List<Category> categories = categoryMapper.selectList(null);
        return R.ok("全部數據查询成功",categories);
    }

    @Override
    public R getByName(String categoryName) {

        QueryWrapper<Category> categoryQueryWrapper = new QueryWrapper<>();
        categoryQueryWrapper.eq("category_name", categoryName);
        Category category = categoryMapper.selectOne(categoryQueryWrapper);

        if (category==null){
            log.info("類別不存在");
            return R.fail("類別不存在");
        }
        log.info("查詢成功",category);
        return R.ok("查詢成功",category);
    }

    @Override
    public R hotsCategory(ProductHotParm productHotParm) {
        QueryWrapper<Category> categoryQueryWrapper = new QueryWrapper<>();
        categoryQueryWrapper.in("category_name", productHotParm.getCategoryName());
        categoryQueryWrapper.select("category_id");
        //查詢資料庫
        List<Object> objects = categoryMapper.selectObjs(categoryQueryWrapper);
        return R.ok("類別查詢成功",objects);
    }
}
