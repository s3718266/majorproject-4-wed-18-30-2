package com.wed18302.majorproject.controller;

import org.springframework.stereotype.Controller;  
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;  
@Controller  
public class WebController {  
	
    @RequestMapping("/login")  
    @ResponseBody  
    public String loginPassword(@RequestParam(required = false) String username, @RequestParam(required = false) String password) {  
    	
    	if (username == null || password == null)
    		return "Invalid auth entry.";
        return "Login: " + username + " Password: " + password;  
    }
    
    
}