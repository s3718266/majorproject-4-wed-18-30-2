package com.wed18302.majorproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class MajorProject {
	 
	private Authentication authentication;
	
	public static void main(String[] args) {
		SpringApplication.run(MajorProject.class, args);
	}
	
	public MajorProject() {
		authentication = new Authentication();
	}
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
	    return encoder;
	}

	
	@Bean
	public Authentication getAuthentication() {
		return authentication;
	}
	
	
}
