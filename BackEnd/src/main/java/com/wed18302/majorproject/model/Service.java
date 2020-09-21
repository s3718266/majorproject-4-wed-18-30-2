package com.wed18302.majorproject.model;

import java.util.Collection;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
