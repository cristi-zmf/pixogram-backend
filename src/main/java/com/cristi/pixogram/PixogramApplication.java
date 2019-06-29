package com.cristi.pixogram;

import com.cristi.pixogram.infra.configurations.AppConfiguration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Import;

@SpringBootApplication
//@EnableEurekaClient
@Import(AppConfiguration.class)
public class PixogramApplication {
	@Value("${EUREKA_URI}")
	private static String eurekaURI;
	public static void main(String[] args) {
		SpringApplication.run(PixogramApplication.class, args);
		System.out.println("Here is the eureka URI: " + eurekaURI);
	}

}

