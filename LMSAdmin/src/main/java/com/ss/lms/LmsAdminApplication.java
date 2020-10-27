package com.ss.lms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.ss.lms.service.AdministratorService;

@EnableAutoConfiguration
@SpringBootApplication
public class LmsAdminApplication {
	
	@Autowired
	AdministratorService adminService;

	public static void main(String[] args) {
		SpringApplication.run(LmsAdminApplication.class, args);
	}
}