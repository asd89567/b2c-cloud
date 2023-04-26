package com.bocky.Carousel;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan(basePackages = "com.bocky.carousel")
@SpringBootApplication
public class CarouselApplication {
    public static void main(String[] args) {
        SpringApplication.run(CarouselApplication.class,args);
    }
}
