package com.loysen.marchmadness.repository;

import com.loysen.marchmadness.model.Actor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Set;

/**
 * Created by kielpedia on 6/29/14.
 */
public interface ActorRepository extends MongoRepository<Actor, String> {

    public Set<Actor> findByTournamentId(ObjectId tournamentId);
}
