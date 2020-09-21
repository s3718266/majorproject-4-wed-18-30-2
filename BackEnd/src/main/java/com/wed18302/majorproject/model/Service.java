package com.wed18302.majorproject.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "services")
public class Service {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int ID;

    @NotNull(message = "Required")
    private String NAME;

    @ManyToOne(fetch = FetchType.EAGER)
    private User ADMIN;
    
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<User> WORKERS;

    @NotNull
    private String TYPE;

    private String DESCRIPTION;

    public Service() {
    }

    public Service(User admin, String type, String name, String description) {
        NAME = name;
        ADMIN = admin;
        TYPE = type;
        DESCRIPTION = description;
    }

    public String getNAME() {
        return NAME;
    }

    public User getADMIN() {
        return ADMIN;
    }

    public String getTYPE() {
        return TYPE;
    }

    public String getDESCRIPTION() {
        return DESCRIPTION;
    }
}
