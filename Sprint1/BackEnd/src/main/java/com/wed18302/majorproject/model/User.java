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
    private int ID;
    private String EMAIL;
    private String PASSWORD;
    private int USERTYPE;
    private String FIRSTNAME;
    private String LASTNAME;
 
    public User() {
    	
    }
    
    public User(String email, String password, int type, String firstname, String lastname) {
    	this.EMAIL = email;
    	this.PASSWORD = password;
    	this.USERTYPE = type;
    	this.FIRSTNAME = firstname;
    	this.LASTNAME = lastname;
    }
    
    public int getId() {
    	return this.ID;
    }
    
    @Override
    public String toString() {
    	return String.format("ID: %d, Email: %s", this.ID, this.EMAIL);
    }
    
    @Override
    public boolean equals(Object obj) {
    	if (obj.getClass() == User.class)
    	{
    		User u = (User)obj;
    		return u.ID == this.ID && u.EMAIL == u.EMAIL;
    	}
    	return true;
    }
    
}
