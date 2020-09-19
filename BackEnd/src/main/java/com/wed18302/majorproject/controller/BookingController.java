package com.wed18302.majorproject.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.wed18302.majorproject.model.User;
import com.wed18302.majorproject.model.UserRepository;
import com.wed18302.majorproject.model.UserType;
import com.wed18302.majorproject.Authentication;
import com.wed18302.majorproject.model.Booking;

@RestController
public class BookingController {

	@Autowired
	Authentication auth;
	
    @RequestMapping("/booking/create")  
    @ResponseBody  
    public String bookingCreate(HttpServletRequest request) {  
    	User user = auth.authenticate(request, UserType.Customer);
    	if (user != null) {
    		return "Permissions are valid.";
    	}
    	return Authentication.INSUFFICIENT_PERMISSIONS;
    }


}
