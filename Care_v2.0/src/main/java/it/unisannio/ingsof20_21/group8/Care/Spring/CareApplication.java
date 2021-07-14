package it.unisannio.ingsof20_21.group8.Care.Spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = { "it.unisannio.ingsof20_21.group8.Care.Spring",
		"it.unisannio.ingsof20_21.group8.Care.controller"} )
public class CareApplication {

	public static void main(String[] args) {
		SpringApplication.run(CareApplication.class, args);
	}

}
