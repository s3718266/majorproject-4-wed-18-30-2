package com.wed18302.majorproject.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.wed18302.majorproject.Authentication;
import com.wed18302.majorproject.model.User;
import com.wed18302.majorproject.model.UserRepository;
import com.wed18302.majorproject.model.UserType;  

@RestController  
public class UserAuthWebController {  
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
    		return Authentication.generateErrorJson("Some fields were left unfilled or not filled correctly.");
    	    	
    	var user = userRepository.findByEMAIL(email);
    	if (user != null) {
    		if ( passwordEncoder.matches(password, user.getPassword()) ) {
    			return Authentication.GenerateTokenFromUser(user);
    		} else {
    			return Authentication.generateErrorJson("Login failure");
    		}
    	}
    	
        return Authentication.generateErrorJson("Account not found.");  
    }
    
    @RequestMapping("/logintoken")  
    @ResponseBody  
    public String loginPassword(String token) {  
    	
        return Authentication.DecodeToken(token) ? "Success" : "Failure";  
    }


    @RequestMapping("/register")  
    @ResponseBody  
    public String registerAccount(HttpServletRequest request) {  
    	
    	String email = request.getParameter("email");
    	String password = request.getParameter("password");
    	String firstname = request.getParameter("firstname");
    	String lastname = request.getParameter("lastname");
    	    	
    	String token = RegisterAccount(UserType.Customer, email, password, firstname, lastname);
    	if (token != "") {
    		return token;
    	}
    	
    	return Authentication.generateErrorJson("Registration failure.");
    }

    @RequestMapping("/registeradmin")  
    @ResponseBody  
    public String registerAdminAccount(HttpServletRequest request) {  
    	
    	String email = request.getParameter("email");
    	String password = request.getParameter("password");
    	String firstname = request.getParameter("firstname");
    	String lastname = request.getParameter("lastname");
    	    	    	
    	String token = RegisterAccount(UserType.Administrator, email, password, firstname, lastname);
    	if (token != "") {
    		return token;
    	}

    	return Authentication.generateErrorJson("Registration failure.");
    }
    
    public String RegisterAccount(UserType userType, String email, String rawPassword, String firstname, String lastname) {
    	// TODO: Improve validation for parameters
    	if (email == null || rawPassword == null || firstname == null || lastname == null)
    		return "";
    	
    	var user = userRepository.findByEMAIL(email);
    	if (user == null) {
	    	var hash = passwordEncoder.encode(rawPassword);
	        var newUser = new User(email, hash, userType.getValue(), firstname, lastname);
	    	userRepository.save(newUser);
	    	return Authentication.GenerateTokenFromUser(newUser);
    	} else {
        	return Authentication.generateErrorJson("Account with the specified email already exists.");
    	}
    	
    }
    
    
}