package com.admin;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

public class SpringBootStartApplication extends SpringBootServletInitializer {

    @Override// 你现在跑的是打包的还是编辑 你进服务器上看
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application){
        return application.sources(AdminApplication.class);
    }
}
