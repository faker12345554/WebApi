package com.person.person.Config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    // 是否开启swagger,正式环境一般是需要关闭的,可根据springboot的多环境配置进行设置
    @Value(value = "${swagger.enabled}")
    Boolean swaggerEnabled;

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(new ApiInfoBuilder()
                        // 页面标题
                        .title("微商城")
                        // 描述
                        .description("好看的皮囊千篇一律,有趣的灵魂百里挑一。")
                        // 作者信息
                        .contact(new Contact("Wen.GuoDong", "https://www.zhihu.com/people/D-Blog/posts", "1770768548@qq.com"))
                        // 版本号
                        .version("1.0.0")
                        .build())
                // 是否开启Swagger注解
                .enable(swaggerEnabled)
                .select()
                // 扫描的路径包
                .apis(RequestHandlerSelectors.basePackage("com.person.person.Personnel.Controller"))
                // 指定路径处理PathSelectors.any()代表所有的路径
                .paths(PathSelectors.any())
                .build()
                .pathMapping("/");
    }
}
