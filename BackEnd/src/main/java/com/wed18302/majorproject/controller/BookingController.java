package com.wed18302.majorproject.controller;

import javax.servlet.http.HttpServletRequest;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.List;

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
    public ResponseEntity<Object> booking_Create(@RequestParam("booking-date") String bookingDate, 
    		@RequestParam("customer-email") String customer, 
    		@RequestParam("worker-email") String worker, 
    		@RequestParam("admin-email") String admin ) {
    	try {
	    	//2020-09-19T09:41:39.808756400Z[UTC]	 
    		
	    	ZonedDateTime now = ZonedDateTime.now(ZoneId.of("UTC"));
	    	ZonedDateTime booked = ZonedDateTime.parse(bookingDate);

	    	User customerUser = userRepo.findByEMAIL(customer);
	    	User workerUser = userRepo.findByEMAIL(worker);
	    	User adminUser = userRepo.findByEMAIL(admin);
	    	
	    	if (customerUser == null)
	    		throw new JsonErrorResponse("Invalid customer email was specified.");
	    	if (workerUser == null)
	    		throw new JsonErrorResponse("Invalid worker email was specified.");
	    	if (adminUser == null)
	    		throw new JsonErrorResponse("Invalid administrator email was specified.");

	    	Booking booking = new Booking(now, booked, customerUser, workerUser, adminUser);
	    	bookingRepo.save(booking);

	        HashMap<String, Object> hmap = new HashMap<String, Object>();
	        hmap.put("booking", booking);
	        
			return ResponseEntity.ok(hmap);
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
    
    @RequestMapping(value="/booking/findByCustomer", method = RequestMethod.POST)  
    @ResponseBody  
    public ResponseEntity<Object> booking_FindByCustomer(@RequestParam("customer-email") String customer ) {
    	try {
	    	//2020-09-19T09:41:39.808756400Z[UTC]	 
	    	User customerUser = userRepo.findByEMAIL(customer);
	    	
	    	if (customerUser == null)
	    		throw new JsonErrorResponse("Invalid customer email was specified.");
	    	
	        HashMap<String, Object> hmap = new HashMap<String, Object>();
	        List<Booking> bookings = bookingRepo.findByCUSTOMER(customerUser);
	        for (Booking booking : bookings)
	        	hmap.put(Integer.toString(booking.getId()), booking);
	        
			return ResponseEntity.ok(hmap);
    	} catch (JsonErrorResponse resp) {
    		return new ResponseEntity<Object>(resp.getMessage(), HttpStatus.OK);
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
		return ResponseEntity.notFound().build();
    }
    
    @RequestMapping(value="/booking/findById", method = RequestMethod.POST)  
    @ResponseBody  
    public ResponseEntity<Object> booking_FindById(@RequestParam("booking-id") int bookingId ) {
    	try {
	    	//2020-09-19T09:41:39.808756400Z[UTC]	 
	    	Booking booking = bookingRepo.findByID(bookingId);
	    	
	    	if (booking == null)
	    		throw new JsonErrorResponse("Booking id could not be found in the database.");
	    	
	        HashMap<String, Object> hmap = new HashMap<String, Object>();
        	hmap.put(Integer.toString(booking.getId()), booking);
	        
			return ResponseEntity.ok(hmap);
    	} catch (JsonErrorResponse resp) {
    		return new ResponseEntity<Object>(resp.getMessage(), HttpStatus.OK);
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
		return ResponseEntity.notFound().build();
    }
    
    @RequestMapping(value="/booking/delete", method = RequestMethod.POST)  
    @ResponseBody  
    public ResponseEntity<Object> booking_DeleteById(@RequestParam("booking-id") int bookingId ) {
    	try {
	    	//2020-09-19T09:41:39.808756400Z[UTC]	 
	    	Booking booking = bookingRepo.findByID(bookingId);
	    	
	    	if (booking == null)
	    		throw new JsonErrorResponse("Booking id could not be found in the database.");
	    	
	    	bookingRepo.delete(booking);
	        HashMap<String, Object> hmap = new HashMap<String, Object>();
	        
			return ResponseEntity.ok(hmap);
    	} catch (JsonErrorResponse resp) {
    		return new ResponseEntity<Object>(resp.getMessage(), HttpStatus.OK);
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
		return ResponseEntity.notFound().build();
    }


}
