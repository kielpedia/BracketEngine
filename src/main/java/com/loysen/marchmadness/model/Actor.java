package com.loysen.marchmadness.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by kielpedia on 6/29/14.
 */
@Document
public class Actor {

    @Id
    private ObjectId id;
    private final ObjectId tournamentId;
    private final String name;

    public Actor(String name, ObjectId tournamentId){
        this.name = name;
        this.tournamentId = tournamentId;
    }

    @JsonSerialize(using = ObjectIdSerializer.class)
    public ObjectId getId() {
        return id;
    }

    @JsonSerialize(using = ObjectIdSerializer.class)
    public ObjectId getTournamentId() {
        return tournamentId;
    }

    public String getName() {
        return name;
    }
}
