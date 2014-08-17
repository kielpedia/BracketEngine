package com.loysen.bracketengine.service.impl;

import com.loysen.bracketengine.model.Tournament;
import com.loysen.bracketengine.repository.TournamentRepository;
import com.loysen.bracketengine.service.TournamentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.springframework.util.Assert.notNull;

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
        return tournamentRepository.findAll();
    }

    @Override
    public Optional<Tournament> findById(String id) {
        notNull(id);

        return Optional.ofNullable(tournamentRepository.findOne(id));
    }

    @Override
    public Tournament create() {
        Tournament tournament = new Tournament();

        return tournamentRepository.save(tournament);
    }

    @Override
    public Tournament update(Tournament tournament) {
        notNull(tournament);

        return tournamentRepository.save(tournament);
    }

    @Override
    public boolean publish(String id) {
        notNull(id);
        Tournament tournament = tournamentRepository.findOne(id);

        if (tournament == null
                || tournament.isPublished()
                || tournament.getActivationDate().isBefore(LocalDateTime.now())) {
            return false;
        }

        tournament.setPublished(true);
        tournamentRepository.save(tournament);

        return true;
    }

    @Override
    public Optional<Tournament> remove(String id) {
        notNull(id);

        Optional tournament = Optional.ofNullable(tournamentRepository.findOne(id));

        if (tournament.isPresent()) {
            tournamentRepository.delete(id);
        }

        return tournament;
    }
}
