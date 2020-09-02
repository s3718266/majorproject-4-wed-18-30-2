package com.wed18302.majorproject.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "bookings")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int ID;

    private int CREATEDTIMESTAMP; // when the booking was created
    @NotNull(message = "Date of when the booking will be is mandatory")
    private int BOOKINGTIMESTAMP; // when the booking is set for
    
    
}
