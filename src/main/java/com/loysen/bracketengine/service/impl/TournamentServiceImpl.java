package com.loysen.bracketengine.service.impl;

import checkers.nullness.quals.NonNull;
import com.loysen.bracketengine.exceptions.TournamentNotReadyException;
import com.loysen.bracketengine.model.Tournament;
import com.loysen.bracketengine.repository.TournamentRepository;
import com.loysen.bracketengine.service.TournamentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * Created by kielpedia on 8/8/14.
 */
@Service
public class TournamentServiceImpl implements TournamentService {

    private TournamentRepository tournamentRepository;

    @Autowired
    public TournamentServiceImpl(TournamentRepository tournamentRepository) {
        this.tournamentRepository = tournamentRepository;
    }

    @Override
    @NonNull
    public List<Tournament> findAll() {
        return tournamentRepository.findAll();
    }

    @Override
    @NonNull
    public Optional<Tournament> findById(@NonNull String id) {
        Assert.notNull(id);

        return Optional.ofNullable(tournamentRepository.findOne(id));
    }

    @Override
    @NonNull
    public Tournament create() {
        return tournamentRepository.save(new Tournament());
    }

    @Override
    @NonNull
    public Optional<Tournament> update(@NonNull Tournament tournament) {
        Assert.notNull(tournament);

        Tournament original = tournamentRepository.findOne(tournament.getId().toString());

        if (original == null) {
            return Optional.ofNullable(null);
        }

        original.setName(tournament.getName());
        original.setDivisions(tournament.getDivisions());


        return Optional.ofNullable(tournamentRepository.save(original));
    }

    @Override
    @NonNull
    public boolean publish(@NonNull String id) {
        Assert.notNull(id);

        Tournament original = tournamentRepository.findOne(id);

        if (original == null) {
            return false;
        }

        // TODO: Add logic to check if it can be published

        original.setPublished(true);
        tournamentRepository.save(original);


        return true;
    }

    @Override
    public Optional<Tournament> remove(String id) {
        Assert.notNull(id);

        Optional tournament = Optional.ofNullable(tournamentRepository.findOne(id));

        if (tournament.isPresent()) {
            tournamentRepository.delete(id);
        }

        return tournament;
    }
}
