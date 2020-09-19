package com.wed18302.majorproject.controller;

import javax.servlet.http.HttpServletRequest;

import java.time.ZoneId;
import java.time.ZonedDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.wed18302.majorproject.model.Booking;
import com.wed18302.majorproject.model.BookingRepository;
import com.wed18302.majorproject.model.User;
import com.wed18302.majorproject.model.UserRepository;
import com.wed18302.majorproject.util.JsonErrorResponse;
import com.wed18302.majorproject.Authentication;

@RestController
public class BookingController {

	@Autowired
	Authentication auth;
	
	@Autowired
	BookingRepository bookingRepo;

	@Autowired
	UserRepository userRepo;
	
    @RequestMapping(value="/booking/create", method = RequestMethod.POST)  
    @ResponseBody  
    public ResponseEntity<Object> bookingCreate(@RequestParam("booking-date") String bookingDate, 
    		@RequestParam("customer-email") String customer, 
    		@RequestParam("employee-email") String employee, 
    		@RequestParam("admin-email") String admin ) {
    	try {
	    	//2020-09-19T09:41:39.808756400Z[UTC]	 
    		
	    	ZonedDateTime now = ZonedDateTime.now(ZoneId.of("UTC"));
	    	ZonedDateTime booked = ZonedDateTime.parse(bookingDate);

	    	User customerUser = userRepo.findByEMAIL(customer);
	    	User employeeUser = userRepo.findByEMAIL(employee);
	    	User adminUser = userRepo.findByEMAIL(admin);
	    	
	    	if (customerUser == null)
	    		throw new JsonErrorResponse("Invalid customer email was specified.");
	    	if (employeeUser == null)
	    		throw new JsonErrorResponse("Invalid employee email was specified.");
	    	if (adminUser == null)
	    		throw new JsonErrorResponse("Invalid administrator email was specified.");
	    	
	    	Booking booking = new Booking(now, booked, customerUser.getId(), employeeUser.getId(), adminUser.getId());
	    	bookingRepo.save(booking);
	    		    	
			return ResponseEntity.ok(booking);
    	} catch (JsonErrorResponse resp) {
    		return new ResponseEntity<Object>(resp.getMessage(), HttpStatus.OK);
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
		return ResponseEntity.notFound().build();
		
    	/*User user = auth.authenticate(request, UserType.Worker);
    	if (user != null) {
    		LocalDate date = LocalDate.now();
    		return date.toString();
    		//return "Permissions are valid.";
    	}
    	return Authentication.INSUFFICIENT_PERMISSIONS;*/
    }


}
