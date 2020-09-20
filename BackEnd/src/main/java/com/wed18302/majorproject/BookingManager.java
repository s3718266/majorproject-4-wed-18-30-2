package com.wed18302.majorproject;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.wed18302.majorproject.model.Booking;
import com.wed18302.majorproject.model.BookingRepository;
import com.wed18302.majorproject.model.User;
import com.wed18302.majorproject.model.UserRepository;
import com.wed18302.majorproject.util.JsonErrorResponse;

public class BookingManager {

	@Autowired
	UserRepository userRepo;
	
	@Autowired
	BookingRepository bookingRepo;
	
	public HashMap<String, Object> makeBooking(String bookingDate, 
			String customerEmail, String workerEmail, String adminEmail) throws JsonErrorResponse {
		ZonedDateTime now = ZonedDateTime.now(ZoneId.of("UTC"));
    	ZonedDateTime booked = ZonedDateTime.parse(bookingDate);

    	User customerUser = userRepo.findByEMAIL(customerEmail);
    	User workerUser = userRepo.findByEMAIL(workerEmail);
    	User adminUser = userRepo.findByEMAIL(adminEmail);
    	
    	if (customerUser == null)
    		throw new JsonErrorResponse("Invalid customer email was specified.");
    	if (workerUser == null)
    		throw new JsonErrorResponse("Invalid worker email was specified.");
    	if (adminUser == null)
    		throw new JsonErrorResponse("Invalid administrator email was specified.");

    	Booking booking = new Booking(now, booked, customerUser, workerUser, adminUser);
    	bookingRepo.save(booking);

        HashMap<String, Object> hmap = new HashMap<String, Object>();
        hmap.put(Integer.toString(booking.getId()), booking);
        
        return hmap;
	}
	
	public HashMap<String, Object> find(int bookingId) throws JsonErrorResponse { 
    	Booking booking = bookingRepo.findByID(bookingId);
    	
    	if (booking == null)
    		throw new JsonErrorResponse("Booking id could not be found in the database.");
    	
        HashMap<String, Object> hmap = new HashMap<String, Object>();
    	hmap.put(Integer.toString(booking.getId()), booking);
    	
    	return hmap;
	}
	
	public HashMap<String, Object> findForCustomer(String customerEmail) throws JsonErrorResponse {
    	User customerUser = userRepo.findByEMAIL(customerEmail);
    	
    	if (customerUser == null)
    		throw new JsonErrorResponse("Invalid customer email was specified.");
    	
        HashMap<String, Object> hmap = new HashMap<String, Object>();
        List<Booking> bookings = bookingRepo.findByCUSTOMER(customerUser);
        for (Booking booking : bookings)
        	hmap.put(Integer.toString(booking.getId()), booking);
        
        return hmap;
	}
	
	public HashMap<String, Object> delete(int bookingId) throws JsonErrorResponse {

    	//2020-09-19T09:41:39.808756400Z[UTC]	 
    	Booking booking = bookingRepo.findByID(bookingId);
    	
    	if (booking == null)
    		throw new JsonErrorResponse("Booking id could not be found in the database.");
    	
    	bookingRepo.delete(booking);
        HashMap<String, Object> hmap = new HashMap<String, Object>();
        return hmap;
	}
	
}
