package com.loysen.bracketengine.model;

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
    private String id;
    @Indexed(sparse = true, unique = true)
    private String name;
    private Set<Match> matches;
    private final String tournamentId;

    public Bracket(String tournamentId) {
        this.tournamentId = tournamentId;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Match> getMatches() {
        return matches;
    }

    public void setMatches(Set<Match> matches) {
        this.matches = matches;
    }

    public String getTournamentId() {
        return tournamentId;
    }
}
