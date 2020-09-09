package com.wed18302.majorproject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class MajorProject {

	@Autowired
	private ErrorAttributes errorAttributes;
	 
	public static void main(String[] args) {
		SpringApplication.run(MajorProject.class, args);
	}
	
	public MajorProject() {
		
	}
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
	    return new BCryptPasswordEncoder();
	}

	
}
