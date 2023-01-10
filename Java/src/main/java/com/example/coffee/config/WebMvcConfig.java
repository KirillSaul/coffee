package com.example.coffee.config;

import com.example.coffee.service.fileManager.FileManagerImpl;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/" + FileManagerImpl.ROOT_FOLDER + "/**")
                .addResourceLocations("file:/" + FileManagerImpl.ABSOLUTE_PATH + "/");
    }
}
