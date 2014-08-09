package com.loysen.bracketengine.service;

import com.loysen.bracketengine.model.Bracket;

import java.util.Optional;
import java.util.Set;

/**
 * Service for reading and manipulating {@link Bracket} objects.
 * <p/>
 * Created by kielpedia on 6/29/14.
 */
public interface BracketService {

    /**
     * Find and return a collection of all {@link Bracket} objects associated with the provided
     * {@link com.loysen.bracketengine.model.Tournament} Id
     *
     * @param tournamentId
     * @return
     */
    Set<Bracket> findAllByTournament(String tournamentId);

    /**
     * Create a {@link Bracket}  for the specified Tournament
     *
     * @param tournamentId
     * @return
     */
    Optional<Bracket> createForTournament(String tournamentId);

    /**
     * Find a {@link Bracket} by its id
     *
     * @param bracketId
     * @return
     */
    Optional<Bracket> findById(String bracketId);

    /**
     * Update a {@link Bracket}
     *
     * @param bracket
     * @return
     */
    Bracket update(Bracket bracket);

    /**
     * Remove a {@link Bracket} by it's id
     *
     * @param tournamentId
     * @param bracketId
     * @return
     */
    Optional<Bracket> remove(String tournamentId, String bracketId);


}
