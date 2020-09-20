package com.wed18302.majorproject;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
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
		
	public List<Booking> makeBooking(String bookingDate, 
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
    	System.out.println(booked);
    	bookingRepo.save(booking);

        List<Booking> list = new ArrayList<Booking>();
        list.add(booking);
        return list;
	}
	
	public List<Booking> find(int bookingId) throws JsonErrorResponse { 
    	Booking booking = bookingRepo.findByID(bookingId);
    	
    	if (booking == null)
    		throw new JsonErrorResponse("Booking id could not be found in the database.");
    	
        List<Booking> list = new ArrayList<Booking>();
        list.add(booking);
    	
        return list;
	}
	
	public List<Booking> findAllForCustomer(String customerEmail) throws JsonErrorResponse {
    	User customerUser = userRepo.findByEMAIL(customerEmail);
    	
    	if (customerUser == null)
    		throw new JsonErrorResponse("Invalid customer email was specified.");
    	
        return bookingRepo.findByCUSTOMER(customerUser);
	}
	
	public List<Booking> delete(int bookingId) throws JsonErrorResponse {

    	//2020-09-19T09:41:39.808756400Z[UTC]	 
    	Booking booking = bookingRepo.findByID(bookingId);
    	
    	if (booking == null)
    		throw new JsonErrorResponse("Booking id could not be found in the database.");
    	
    	bookingRepo.delete(booking);
    	return new ArrayList<Booking>();
	}
		
}
