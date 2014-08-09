package com.loysen.bracketengine.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;

/**
 * Created by kielpedia on 5/31/14.
 */
@Document
public class Bracket {

    @Id
    private ObjectId id;
    @Indexed(sparse = true, unique = true)
    private final String name;
    private Set<Match> matches;
    private ObjectId tournamentId;

    public Bracket(String name, ObjectId tournamentId) {
        this.name = name;
        this.tournamentId = tournamentId;
    }

    @JsonSerialize(using = ObjectIdSerializer.class)
    public ObjectId getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Set<Match> getMatches() {
        return matches;
    }

    @JsonSerialize(using = ObjectIdSerializer.class)
    public ObjectId getTournamentId() {
        return tournamentId;
    }
}
