package com.wed18302.majorproject.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.wed18302.majorproject.interfaces.GenericWebJsonResponse;

public class WebResponseUtil {

    public static ResponseEntity<Object> genericWebResponse(GenericWebJsonResponse response) {
    	try {
        	/*User user = auth.authenticate(request, UserType.Worker);
        	if (user != null) {
        		
        		//return "Permissions are valid.";
        	}
        	throw new JsonErrorResponse(Authentication.INSUFFICIENT_PERMISSIONS);*/
			return ResponseEntity.ok(response.getResponse());
    	} catch (JsonErrorResponse resp) {
    		return new ResponseEntity<Object>(resp.getMessage(), HttpStatus.OK);
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
		return ResponseEntity.notFound().build();
    }
    
}
