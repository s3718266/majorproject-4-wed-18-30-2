package com.wed18302.majorproject.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "services")
public class Service {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int ID;

    @NotNull(message = "Date of when the booking was created")
    @JsonProperty("createdTimestamp")
    private String CREATEDTIMESTAMP; // when the booking was created
    
    @NotNull(message = "Required")
    private String NAME;

    @NotNull
    @OneToOne(fetch = FetchType.EAGER)
    private User ADMIN;
        
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<User> WORKERS;

    @NotNull
    private String TYPE;

    @NotNull
    private String DESCRIPTION;

    public Service() {
    }

    public Service(String date, User admin, String type, String name, String description) {
    	CREATEDTIMESTAMP = date;
        NAME = name;
        ADMIN = admin;
        TYPE = type;
        DESCRIPTION = description;
    }

    public int getId() {
    	return ID;
    }
    
    public String getName() {
        return NAME;
    }

    public User getAdmin() {
        return ADMIN;
    }

    public String getType() {
        return TYPE;
    }
    
    public List<User> getWorkers() {
    	return this.WORKERS;
    }

    public String getDescription() {
        return DESCRIPTION;
    }
}
