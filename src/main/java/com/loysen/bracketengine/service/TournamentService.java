package com.loysen.bracketengine.service;

import checkers.nullness.quals.NonNull;
import com.loysen.bracketengine.exceptions.TournamentNotReadyException;
import com.loysen.bracketengine.model.Tournament;

import java.util.List;
import java.util.Optional;

/**
 * Service for Reading and Manipulating {@link Tournament} objects
 * <p>
 * Created by kielpedia on 8/8/14.
 */
public interface TournamentService {

    /**
     * Find and return a collection of all {@link Tournament}  objects
     *
     * @return
     */
    @NonNull
    List<Tournament> findAll();

    /**
     * Find a {@link Tournament} by its id
     *
     * @param id
     * @return Optional with Tournament if found, empty if not
     */
    @NonNull
    Optional<Tournament> findById(String id);

    /**
     * Create a new {@link Tournament}
     *
     * @return
     */
    @NonNull
    Tournament create();

    /**
     * Update the {@link Tournament}  record
     *
     * @return
     */
    @NonNull
    Optional<Tournament> update(@NonNull Tournament tournament);

    /**
     * Publish an existing {@link Tournament}  if it is in the correct state
     *
     * @param id
     * @return
     */
    @NonNull
    boolean publish(@NonNull String id);

    /**
     * Remove a {@link Tournament} if it is not active
     *
     * @param id
     * @return Optional with Tournament if removed, empty if not
     */
    Optional remove(String id);

}
