package com.cristi.mentool.mentool.infra.configurations;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.client.RestTemplate;

@EnableJpaRepositories(basePackages = AppConfiguration.BASE_PACKAGES)
@ComponentScan(basePackages = AppConfiguration.BASE_PACKAGES)
public class AppConfiguration {
    static final String BASE_PACKAGES = "com.cristi.mentool.mentool";

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
