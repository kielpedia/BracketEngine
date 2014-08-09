package com.loysen.bracketengine.service.impl;

import com.loysen.bracketengine.model.Actor;
import com.loysen.bracketengine.repository.ActorRepository;
import com.loysen.bracketengine.service.ActorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

/**
 * Created by kielpedia on 8/8/14.
 */
@Service
public class ActorServiceImpl implements ActorService {

    private ActorRepository actorRepository;

    @Autowired
    public ActorServiceImpl(ActorRepository actorRepository) {
        this.actorRepository = actorRepository;
    }

    @Override
    public Set<Actor> findAllByTournament(String tournamentId) {
        return null;
    }

    @Override
    public Optional<Actor> createForTournament(String tournamentId) {
        return null;
    }

    @Override
    public Actor update(Actor actor) {
        return null;
    }

    @Override
    public Optional<Actor> remove(String tournamentId, String actorId) {
        return null;
    }
}
