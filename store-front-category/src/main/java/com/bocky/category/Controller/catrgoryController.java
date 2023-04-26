package com.bocky.category.Controller;

import com.bocky.category.Service.CategoryService;
import com.bocky.parm.ProductHotParm;
import com.bocky.pojo.Category;
import com.bocky.utils.R;
import io.netty.util.internal.StringUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 　@Author: Bocky
 * 　@Date: 2023-04-22-下午 04:37
 * 　@Description:
 */
@RestController
@RequestMapping("/category")
public class catrgoryController {

    @Resource
    private CategoryService categoryService;

    @GetMapping("/promo/{categoryName}")
    public R getName(@PathVariable String categoryName) {
        if (StringUtils.isEmpty(categoryName)){
            return R.fail("類別為NULL");
        }
        return R.ok(categoryService.getByName(categoryName));
    }

    /**
     * 熱門類別查詢
     * @param productHotParm
     * @param bindingResult
     * @return
     */
    @PostMapping("/hots")
    public R hotscategory(@RequestBody @Validated ProductHotParm productHotParm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return R.fail(bindingResult.getFieldError().getDefaultMessage());
        }
        return categoryService.hotsCategory(productHotParm);

    }

    @GetMapping("/list")
    public R list(){
        return categoryService.list();
    }
}
