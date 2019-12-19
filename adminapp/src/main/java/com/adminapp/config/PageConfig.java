package com.adminapp.config;

import com.github.pagehelper.PageHelper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

@Configuration
public class PageConfig {
    @Bean
      public PageHelper pageHelper(){
                 PageHelper pageHelper = new PageHelper();
                 Properties properties = new Properties();
                 properties.setProperty("offsetAsPageNum","true");
                 properties.setProperty("rowBoundsWithCount","true");
                 properties.setProperty("reasonable","true");
                 properties.setProperty("dialect","PostgreSQL");    //配置PostgreSQL数据库的方言是不是和这个配置有关 我没配这个
                 pageHelper.setProperties(properties);
                 return pageHelper;
             }
}
