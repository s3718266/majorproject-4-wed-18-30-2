package com.wed18302.majorproject.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long ID;
    private String EMAIL;
    private String PASSWORD;
    private int USERTYPE;
    private String FIRSTNAME;
    private String LASTNAME;
 
    
}
