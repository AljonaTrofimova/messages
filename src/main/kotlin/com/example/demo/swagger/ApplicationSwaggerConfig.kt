package com.example.demo.swagger

import com.google.common.base.Predicate
import com.google.common.base.Predicates
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import springfox.documentation.builders.ApiInfoBuilder
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.service.ApiInfo
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2

@Configuration
@EnableSwagger2
class ApplicationSwaggerConfig {
    @Bean
    fun endpoint(): Docket {
        return Docket(DocumentationType.SWAGGER_2)
                .enable(true)
                .groupName("version 1.0")
                .apiInfo(apiInfo())
                .useDefaultResponseMessages(false)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(paths())
                .build()
    }

    private fun apiInfo(): ApiInfo {
        return ApiInfoBuilder().title("Message service API").version("1.0").build()
    }

    private fun paths(): Predicate<String> {
        return Predicates.or(PathSelectors.regex("/messages*"),
                Predicates.or(PathSelectors.regex("/messages/message*"),
                        Predicates.or(PathSelectors.regex("/messages/v1/latest-message*"),
                                Predicates.or(PathSelectors.regex("/messages/v2/latest-message*")
                                ))))
    }
}