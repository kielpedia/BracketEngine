package com.loysen.marchmadness.repository;

import com.loysen.marchmadness.model.Bracket;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Set;

/**
 * Created by kielpedia on 6/28/14.
 */
public interface BracketRepository extends MongoRepository<Bracket, String> {

    public Bracket findByName(String bracketName);

    public Set<Bracket> findByTournamentId(ObjectId tournamentId);

}
