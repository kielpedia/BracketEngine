package com.loysen.bracketengine.service.impl;

import com.loysen.bracketengine.exceptions.TournamentNotReadyException;
import com.loysen.bracketengine.model.Tournament;
import com.loysen.bracketengine.repository.TournamentRepository;
import com.loysen.bracketengine.service.TournamentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public List<Tournament> findAll() {
        return null;
    }

    @Override
    public Optional<Tournament> findById(String id) {
        return null;
    }

    @Override
    public Tournament create() {
        return null;
    }

    @Override
    public Tournament update(Tournament tournament) {
        return null;
    }

    @Override
    public Optional<Tournament> publish(String id) throws TournamentNotReadyException {
        return null;
    }

    @Override
    public Optional<Tournament> remove(String id) {
        return null;
    }
}
