package com.admin.filter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


/***
 2  * 新建Token拦截器
 3 * @Title: InterceptorConfig.java
 4 * @author MRC
 5 * @date 2019年5月27日 下午5:33:28
 6 * @version V1.0
 7  */
 @Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
                registry.addInterceptor(authenticationInterceptor())
                        .addPathPatterns("/**");    // 拦截所有请求，通过判断是否有 @LoginRequired 注解 决定是否需要登录
             }
     @Bean
     public AuthenticationInterceptor authenticationInterceptor() {
                 return new AuthenticationInterceptor();// 自己写的拦截器
             }

}
