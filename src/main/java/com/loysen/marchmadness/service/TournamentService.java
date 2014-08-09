package com.loysen.marchmadness.service;

import com.loysen.marchmadness.exceptions.TournamentNotReadyException;
import com.loysen.marchmadness.model.Tournament;

import java.util.List;
import java.util.Optional;

/**
 * Service for Reading and Manipulating {@link Tournament} objects
 * <p/>
 * Created by kielpedia on 8/8/14.
 */
public interface TournamentService {

    /**
     * Find and return a collection of all {@link Tournament}  objects
     *
     * @return
     */
    List<Tournament> findAll();

    /**
     * Find a {@link Tournament} by its id
     *
     * @param id
     * @return Optional with Tournament if found, empty if not
     */
    Optional<Tournament> findById(String id);

    /**
     * Create a new {@link Tournament}
     *
     * @return
     */
    Tournament create();

    /**
     * Update the {@link Tournament}  record
     *
     * @return
     */
    Tournament update(Tournament tournament);

    /**
     * Publish an existing {@link Tournament}  if it is in the correct state
     *
     * @param id
     * @return
     * @throws TournamentNotReadyException
     */
    Optional<Tournament> publish(String id) throws TournamentNotReadyException;

    /**
     * Remove a {@link Tournament} if it is not active
     *
     * @param id
     * @return Optional with Tournament if removed, empty if not
     */
    Optional<Tournament> remove(String id);

}
