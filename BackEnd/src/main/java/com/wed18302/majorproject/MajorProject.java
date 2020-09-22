package com.wed18302.majorproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class MajorProject {

	private ServiceManager serviceManager;
	private BookingManager bookingManager;
	private Authentication authentication;
	
	public static void main(String[] args) {
		SpringApplication.run(MajorProject.class, args);
	}
	
	public MajorProject() {
		authentication = new Authentication();
		bookingManager = new BookingManager();
		serviceManager = new ServiceManager();
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
	
	@Bean
	public BookingManager getBookingManager() {
		return bookingManager;
	}
	
	@Bean
	public ServiceManager getServiceManager() {
		return serviceManager;
	}
	
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowedOrigins("http://localhost:3000");
 			}
	 	};
	}
	
}
