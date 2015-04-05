package com.loysen.bracketengine.model;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Calendar;
import java.util.Set;

/**
 * Created by kielpedia on 6/29/14.
 */
@Document
public class Tournament {

    @Id
    private String id;
    @NotBlank
    private String name;
    @NotEmpty
    private Set<String> divisions;
    private boolean published = false;

    private Calendar activationDate;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }

    public Set<String> getDivisions() {
        return divisions;
    }

    public void setDivisions(Set<String> divisions) {
        this.divisions = divisions;
    }

    public boolean isPublished() {
        return published;
    }

    public void setPublished(boolean published) {
        this.published = published;
    }

    public Calendar getActivationDate() {
        return activationDate;
    }

    public void setActivationDate(Calendar activationDate) {
        this.activationDate = activationDate;
    }


}
