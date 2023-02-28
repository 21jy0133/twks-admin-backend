package com.kh.twks;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@RestController
@ServletComponentScan
public class TwksApplication extends SpringBootServletInitializer implements WebMvcConfigurer {

	@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(TwksApplication.class);
    }

    public static void main(String[] args) throws Exception {
		System.out.println(Thread.currentThread().getName());
        SpringApplication.run(TwksApplication.class, args);
		//System.out.println(Thread.currentThread().getName());
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // H:\twksfile\img\ScreenShot
        registry.addResourceHandler("/static/screenshot/**").addResourceLocations("file:Z:/twksfile/img/ScreenShot/");

        //registry.addResourceHandler("/static/screenshot/**").addResourceLocations("file:/Users/yue/Desktop/screenshots/");
    }

}
