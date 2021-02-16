package com.mindtree.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;
import org.springframework.beans.factory.annotation.Autowired;

@SpringBootApplication
public class AirlineApplication {

	@Autowired
	private Environment env;

	public static void main(String[] args) {
	        System.out.println(env.getProperty("mysql_service")+"==============================================");
		SpringApplication.run(AirlineApplication.class, args);
	}

}
