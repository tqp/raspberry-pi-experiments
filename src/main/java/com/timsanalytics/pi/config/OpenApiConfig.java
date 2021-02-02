package com.timsanalytics.pi.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.tags.Tag;
import org.springdoc.api.OpenApiCustomiser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Comparator;
import java.util.stream.Collectors;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .components(new Components())
                .info(new Info()
                        .title("Tim's Raspberry Pi")
                        .description("API for Raspberry Pi Experiments")
                );
    }

    @Bean
    OpenApiCustomiser sortTagsAlphabetically() {
        return openAPI -> openAPI.setTags(customOpenAPI().getTags().stream()
                .sorted(Comparator.comparing(Tag::getName))
                .collect(Collectors.toList()));
    }
}
