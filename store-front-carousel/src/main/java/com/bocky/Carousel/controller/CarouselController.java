package com.bocky.Carousel.controller;

import com.bocky.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.bocky.Carousel.service.impl.CarouselServiceimpl;

@RestController
@RequestMapping("carousel")
public class CarouselController {

    @Autowired
    private CarouselServiceimpl carouselService;

    @PostMapping("list")
    public R list(){
        return carouselService.list();
    }

}
