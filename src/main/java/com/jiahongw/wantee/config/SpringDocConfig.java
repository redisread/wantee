package com.jiahongw.wantee.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * SpringDoc API文档相关配置
 *
 * @author VictorHong
 * @date 2023/4/15
 */
@Configuration
public class SpringDocConfig {

    @Bean
    public OpenAPI wanteeOpenAPI() {
        return new OpenAPI()
            .info(new Info().title("Wantee API")
                .description("Wantee API 演示")
                .version("v1.0.0")
                .license(new License().name("Apache 2.0")
                    .url("https://github.com/macrozheng/mall-learning")))
            .externalDocs(new ExternalDocumentation()
                .description("SpringBoot实战电商项目mall（50K+Star）全套文档")
                .url("http://www.macrozheng.com"));
    }

    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
            .group("notion")
            .pathsToMatch("/notion/**")
            .build();
    }

    @Bean
    public GroupedOpenApi noteApi() {
        return GroupedOpenApi.builder()
            .group("note")
            .pathsToMatch("/note/**")
            .build();
    }
}
