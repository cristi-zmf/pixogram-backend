package com.cristi.pixogram;

import com.cristi.pixogram.infra.configurations.AppConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@EnableEurekaClient
@Import(AppConfiguration.class)
public class PixogramApplication {
	public static void main(String[] args) {
		SpringApplication.run(PixogramApplication.class, args);
	}

}

