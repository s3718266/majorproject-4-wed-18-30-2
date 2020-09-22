package com.wed18302.majorproject;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.wed18302.majorproject.model.Booking;
import com.wed18302.majorproject.model.BookingRepository;
import com.wed18302.majorproject.model.Service;
import com.wed18302.majorproject.model.ServiceRepository;
import com.wed18302.majorproject.model.User;
import com.wed18302.majorproject.model.UserRepository;
import com.wed18302.majorproject.util.JsonErrorResponse;

public class BookingManager {

	@Autowired
	ServiceRepository serviceRepo;
	
	@Autowired
	UserRepository userRepo;
	
	@Autowired
	BookingRepository bookingRepo;
	
	public HashMap<String, Object> makeBooking(int serviceId, String bookingDate,
			int customerId, int workerId) throws JsonErrorResponse {
		ZonedDateTime now = ZonedDateTime.now(ZoneId.of("UTC"));
    	ZonedDateTime booked = ZonedDateTime.parse(bookingDate);

    	Service service = serviceRepo.findByID(serviceId);
    	User customerUser = userRepo.findByID(customerId);
    	User workerUser = userRepo.findByID(workerId);

    	if (service == null)
    		throw new JsonErrorResponse("Invalid service id was specified.");
    	if (customerUser == null)
    		throw new JsonErrorResponse("Invalid customer id was specified.");
    	if (workerUser == null)
    		throw new JsonErrorResponse("Invalid worker id was specified.");
    	if (!service.getWorkers().contains(workerUser))
    		throw new JsonErrorResponse("Worker does not work for service.");

    	Booking booking = new Booking(service, now, booked, customerUser, workerUser);
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
	
	public HashMap<String, Object> findForCustomer(int customerId) throws JsonErrorResponse {
    	User customerUser = userRepo.findByID(customerId);
    	
    	if (customerUser == null)
    		throw new JsonErrorResponse("Invalid customer id was specified.");
    	
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
