package com.blibli.blibook.backend;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication(exclude = {
		org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class})
@EnableCaching
public class Application {
	public static void main(String[] args) {
		new SpringApplicationBuilder(Application.class).run(args);
	}
}
