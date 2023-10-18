package com.example.mainapp.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/products ").setViewName("products");
       // registry.addViewController("/registration").setViewName("userPage");
    }//   registry.addViewController("/hello").setViewName("index");
       // registry.addViewController("/auth/register").setViewName("userPage");

}