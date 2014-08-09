package com.loysen.marchmadness.service;

import com.loysen.marchmadness.model.Actor;

import java.util.Optional;
import java.util.Set;

/**
 * Service for reading and manipulation {@link Actor} objects
 * <p/>
 * Created by kielpedia on 8/8/14.
 */
public interface ActorService {

    /**
     * Find and return a collection of {@link Actor} objects for the given
     * {@link com.loysen.marchmadness.model.Tournament}
     *
     * @param tournamentId
     * @return
     */
    Set<Actor> findAllByTournament(String tournamentId);

    /**
     * Create a new {@link Actor} for a {@link com.loysen.marchmadness.model.Tournament}
     *
     * @param tournamentId {@link com.loysen.marchmadness.model.Tournament} id to link the actor to
     * @return
     */
    Optional<Actor> createForTournament(String tournamentId);

    /**
     * @param actor
     * @return
     */
    Actor update(Actor actor);

    /**
     * @param actorId
     * @return
     */
    Optional<Actor> remove(String tournamentId, String actorId);

}
