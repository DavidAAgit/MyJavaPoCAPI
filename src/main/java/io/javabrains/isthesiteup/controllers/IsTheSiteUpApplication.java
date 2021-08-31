// java main pack
package io.javabrains.isthesiteup.controllers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
 

 
@SpringBootApplication
public class IsTheSiteUpApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(IsTheSiteUpApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder){
		return builder.sources(IsTheSiteUpApplication.class);
	}
}
