package com.outsourcebooster.cryptocurrency.web.config

/**
 * Created by rklimemnko on 29.05.2016.
 */

import com.outsourcebooster.cryptocurrency.common.util.ApplicationUtils
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter

@Configuration
open class MvcConfig : WebMvcConfigurerAdapter() {

    override fun addViewControllers(registry: ViewControllerRegistry?) {
        registry!!.addViewController("/").setViewName("index")
        registry.addViewController("/index").setViewName("index")
        registry.addViewController("/login").setViewName("login")
    }

    override fun addResourceHandlers(registry: ResourceHandlerRegistry?) {
        registry!!.addResourceHandler("img/user/**").addResourceLocations("file:///" + ApplicationUtils.getCommonProperty("cryptocurrency.web.images.path"))
    }

    override fun addCorsMappings(registry: CorsRegistry?) {
        registry!!.addMapping("/**")
                .allowedOrigins("http://localhost:4200")
    }
}