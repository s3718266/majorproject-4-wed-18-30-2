package com.wed18302.majorproject.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "users")
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int ID;
    
    @Email(message = "Email is mandatory")
    private String EMAIL;
    @NotNull(message = "Password is mandatory")
    private String PASSWORD;
    @NotNull(message = "First name is mandatory")
    private String FIRSTNAME;
    @NotNull(message = "Last name is mandatory")
    private String LASTNAME;
    
    private int USERTYPE;
 
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
    
    public String getEmail() {
    	return this.EMAIL;
    }
    
    public String getPassword() {
    	return this.PASSWORD;
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
