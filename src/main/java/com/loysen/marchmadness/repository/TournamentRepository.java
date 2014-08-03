package com.loysen.marchmadness.repository;

import com.loysen.marchmadness.model.Tournament;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by kielpedia on 6/29/14.
 */
public interface TournamentRepository extends MongoRepository<Tournament, String> {

}
