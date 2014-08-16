package com.loysen.bracketengine.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by kielpedia on 6/29/14.
 */
@Document
public class Actor {

    @Id
    private String id;
    private final String tournamentId;
    private String name;


    public Actor(String name, String tournamentId) {
        this.name = name;
        this.tournamentId = tournamentId;
    }

    public String getId() {
        return id;
    }

    public String getTournamentId() {
        return tournamentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
