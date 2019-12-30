package com.prisonapp.config;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestHeader;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;


@Configuration
@EnableSwagger2
public class SwaggerConfig {
    // 是否开启swagger,正式环境一般是需要关闭的,可根据springboot的多环境配置进行设置
    @Value(value = "${swagger.enabled}")
    Boolean swaggerEnabled;
    @Bean
    public Docket createRestApi() {
        //出现token参数


        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(new ApiInfoBuilder()
                        // 页面标题
                        .title("微商城")
                        // 描述
                        .description("取保监居工作人员App接口")
                        // 作者信息
                        .contact(new Contact("Wen.GuoDong", "https://www.zhihu.com/people/D-Blog/posts", "2232243082@qq.com"))
                        // 版本号
                        .version("1.0.0")
                        .build())
                // 是否开启Swagger注解
                .enable(swaggerEnabled)
                .select()
                // 扫描的路径包
                .apis(RequestHandlerSelectors.basePackage("com.prisonapp.business.controller"))
                // 指定路径处理PathSelectors.any()代表所有的路径
                .paths(PathSelectors.any())
                .build()
                .pathMapping("/");
    }

//    @Bean
//    public Docket apiDocument() {
//        return new Docket(DocumentationType.SWAGGER_2)
//                .groupName("api")
//                .select() .apis(RequestHandlerSelectors.withClassAnnotation(Api.class))
//                .paths(regex("/api.*"))
//                .build()
//                .pathMapping("/")
//                .globalOperationParameters(setHeaderToken())
//                .apiInfo(metadata());
//    }
//    private List<Parameter> setHeaderToken() {
//        ParameterBuilder tokenPar = new ParameterBuilder();
//        List<Parameter> pars = new ArrayList<>();
//        tokenPar.name("X-Auth-Token").description("token").modelRef(new ModelRef("string")).parameterType("header").required(false).build();
//        pars.add(tokenPar.build());
//        return pars;
//    }
}
