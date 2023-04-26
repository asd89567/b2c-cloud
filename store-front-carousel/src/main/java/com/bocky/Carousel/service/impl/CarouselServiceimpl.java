package com.bocky.Carousel.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bocky.Carousel.service.CarouselService;
import com.bocky.pojo.Carousel;
import com.bocky.utils.R;
import lombok.extern.log4j.Log4j2;
import com.bocky.Carousel.mapper.CarouselMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;


@Log4j2
@Service
public class CarouselServiceimpl implements CarouselService {
    @Resource
    private CarouselMapper carouselMapper;
    /**
     * 查詢優先擊最高的六條
     * 按照優先擊查詢
     * 使用stream流切割
     * @return
     */
    public R list() {
        QueryWrapper<Carousel> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("priority");
        List<Carousel> carousels = carouselMapper.selectList(queryWrapper);

        List<Carousel> collect = carousels.stream().limit(6).collect(Collectors.toList());
        R ok = R.ok(collect);
        log.info("CarouselServiceimpl,結果:{}",ok);
        return ok;
    }
}
