package com.kryspetrie.reactivedemo.bootstrap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.r2dbc.config.EnableR2dbcAuditing;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;
import org.springframework.web.reactive.config.EnableWebFlux;

@EnableR2dbcRepositories(basePackages = "com.kryspetrie.reactivedemo.infrastructure.repository")
@EnableR2dbcAuditing
@SpringBootApplication
@ComponentScan(basePackages = "com.kryspetrie.reactivedemo")
@EnableWebFlux
public class DemoApplication {
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
}
