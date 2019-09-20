package com.toptal.apartment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication
@ComponentScan("com.toptal")
@EnableJpaRepositories("com.toptal")
@EntityScan("com.toptal")
@CrossOrigin
@EnableAutoConfiguration
public class ApartmentRentalsApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApartmentRentalsApplication.class, args);
	}

}
