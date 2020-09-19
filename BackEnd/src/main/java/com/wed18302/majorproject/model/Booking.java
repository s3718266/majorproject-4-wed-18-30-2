package com.wed18302.majorproject.model;

import java.time.ZonedDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@Entity
@Table(name = "bookings")
@JsonRootName(value = "booking")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int ID;
    
    @NotNull(message = "Date of when the booking was created")
    @JsonProperty("createdTimestamp")
    private String CREATEDTIMESTAMP; // when the booking was created
    @NotNull(message = "Date of when the booking will be is mandatory")
    @JsonProperty("bookingTimestamp")
    private String BOOKINGTIMESTAMP; // when the booking is set for
    @NotNull
    @JsonProperty("customerId")
    private int CUSTOMERID;
    @NotNull
    @JsonProperty("employeeId")
    private int EMPLOYEEID;
    @NotNull
    @JsonProperty("adminId")
    private int ADMINID;
    
    public Booking() {
    }
    
    public Booking(ZonedDateTime created, ZonedDateTime booking, int customer, int employee, int admin) {
    	this.CREATEDTIMESTAMP = created.toString();
    	this.BOOKINGTIMESTAMP = booking.toString();
    	this.CUSTOMERID = customer;
    	this.EMPLOYEEID = employee;
    	this.ADMINID = admin;
    }
    
    
}
