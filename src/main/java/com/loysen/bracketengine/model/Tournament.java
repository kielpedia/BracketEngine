package com.loysen.bracketengine.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Set;

/**
 * Created by kielpedia on 6/29/14.
 */
@Document
public class Tournament {

    @Id
    private ObjectId id;
    private String name;
    private Set<String> divisions;
    private boolean published = false;
    private LocalDateTime activationDate;



    @JsonSerialize(using = ObjectIdSerializer.class)
    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
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

    public LocalDateTime getActivationDate() {
        return activationDate;
    }

    public void setActivationDate(LocalDateTime activationDate) {
        this.activationDate = activationDate;
    }


}
