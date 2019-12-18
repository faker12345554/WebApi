package com.adminapp.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MyWebMvcConfigurer implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        //设置允许跨域的路径
        registry.addMapping("/**")
                //设置允许跨域请求的域名
                .allowedOrigins("*")
                .allowedHeaders("*")
//                .allowCredentials(true)//是否允许证书 不再默认开启
                //设置允许的方法
                .allowedMethods("GET", "POST", "PUT", "DELETE");
//                .maxAge(3600);//跨域允许时间
    }
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //   registry.addInterceptor(new MyInterceptorConfig ());
    }
}
