package com.loysen.bracketengine.repository;

import com.loysen.bracketengine.model.Tournament;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by kielpedia on 6/29/14.
 */
public interface TournamentRepository extends MongoRepository<Tournament, String> {

}
