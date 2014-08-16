package com.loysen.bracketengine.service.impl;

import com.loysen.bracketengine.model.Actor;
import com.loysen.bracketengine.model.Tournament;
import com.loysen.bracketengine.repository.ActorRepository;
import com.loysen.bracketengine.service.ActorService;
import com.loysen.bracketengine.service.TournamentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

import static org.springframework.util.Assert.notNull;

/**
 * Created by kielpedia on 8/8/14.
 */
@Service
public class ActorServiceImpl implements ActorService {

    private ActorRepository actorRepository;
    private TournamentService tournamentService;

    @Autowired
    public ActorServiceImpl(ActorRepository actorRepository, TournamentService tournamentService) {
        this.actorRepository = actorRepository;
        this.tournamentService = tournamentService;
    }

    @Override
    public Set<Actor> findAllByTournament(String tournamentId) {
        notNull(tournamentId);

        return actorRepository.findByTournamentId(tournamentId);
    }

    @Override
    public Optional<Actor> createForTournament(String name, String tournamentId) {
        notNull(name);
        notNull(tournamentId);

        Optional<Tournament> tournamentOptional = tournamentService.findById(tournamentId);
        //if the Tournament doesn't exist, dont create a new Actor
        if (!tournamentOptional.isPresent()) {
            return Optional.empty();
        }

        Optional<Actor> actorOptional = Optional.ofNullable(actorRepository.findByNameAndTournamentId(name,
                tournamentId));
        //If an Actor with the same name exists for this Tournament, dont create a new Actor
        if (actorOptional.isPresent()) {
            return Optional.empty();
        }

        Actor newActor = new Actor(name, tournamentId);
        return Optional.of(actorRepository.save(newActor));
    }

    @Override
    public Actor update(Actor actor) {
        notNull(actor);

        return actorRepository.save(actor);
    }

    @Override
    public Optional<Actor> remove(String tournamentId, String actorId) {
        notNull(tournamentId);
        notNull(actorId);

        Optional<Actor> actorOptional = Optional.ofNullable(actorRepository.findOne(actorId));

        if (!actorOptional.isPresent() || !actorOptional.get().getTournamentId().equals(tournamentId)) {
            return Optional.empty();
        }

        actorRepository.delete(actorOptional.get());

        return actorOptional;
    }
}
