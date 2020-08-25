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

@RestController  
public class WebController {  
	@Autowired
	private PasswordEncoder passwordEncoder;
	
    @Autowired
    private UserRepository userRepository;
    
    @RequestMapping("/login")  
    @ResponseBody  
    public String loginPassword(HttpServletRequest request) {  

    	String email = request.getParameter("email");
    	String password = request.getParameter("password");

    	// TODO: Improve validation for parameters
    	if (email == null || password == null)
    		return "Invalid auth entry.";
    	    	
    	var user = userRepository.findByEMAIL(email);
    	if (user != null) {
    		if ( passwordEncoder.matches(password, user.getPassword()) ) {
    			return "Login successful";
    		} else {
    			return "Login failure";
    		}
    	}
    	
        return "Account not found.";  
    }


    @RequestMapping("/register")  
    @ResponseBody  
    public String registerAccount(HttpServletRequest request) {  
    	
    	String email = request.getParameter("email");
    	String password = request.getParameter("password");
    	String firstname = request.getParameter("firstname");
    	String lastname = request.getParameter("lastname");
    	    	    	    	
    	if (RegisterAccount(UserType.Customer, email, password, firstname, lastname)) {

            return "Registered new customer account with Email: " + email + "and Password: " + password; 
    	}
    	
    	return "Registration failed.";
    }

    @RequestMapping("/registeradmin")  
    @ResponseBody  
    public String registerAdminAccount(HttpServletRequest request) {  
    	
    	String email = request.getParameter("email");
    	String password = request.getParameter("password");
    	String firstname = request.getParameter("firstname");
    	String lastname = request.getParameter("lastname");
    	    	    	    	
    	if (RegisterAccount(UserType.Administrator, email, password, firstname, lastname)) {

            return "Registered new administrator account with Email: " + email + "and Password: " + password; 
    	}
    	
    	return "Registration failed.";
    }
    
    public boolean RegisterAccount(UserType userType, String email, String rawPassword, String firstname, String lastname) {
    	// TODO: Improve validation for parameters
    	if (email == null || rawPassword == null || firstname == null || lastname == null)
    		return false;
    	
    	var user = userRepository.findByEMAIL(email);
    	if (user == null) {
	    	var hash = passwordEncoder.encode(rawPassword);
	        var newUser = new User(email, hash, userType.getValue(), firstname, lastname);
	    	userRepository.save(newUser);
    	} else {
    		return false; // user already exists
    	}
    	
    	return true;
    }
    
    
}