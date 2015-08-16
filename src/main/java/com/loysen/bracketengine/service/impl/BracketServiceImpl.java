package com.loysen.bracketengine.service.impl;

import com.loysen.bracketengine.model.Actor;
import com.loysen.bracketengine.model.Bracket;
import com.loysen.bracketengine.model.Tournament;
import com.loysen.bracketengine.repository.BracketRepository;
import com.loysen.bracketengine.service.BracketService;
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
public class BracketServiceImpl implements BracketService {

    private BracketRepository bracketRepository;
    private TournamentService tournamentService;

    @Autowired
    public BracketServiceImpl(BracketRepository bracketRepository, TournamentService tournamentService) {
        this.bracketRepository = bracketRepository;
        this.tournamentService = tournamentService;
    }

    @Override
    public Set<Bracket> findAllByTournament(String tournamentId) {
        notNull(tournamentId);


        return bracketRepository.findByTournamentId(tournamentId);
    }

    @Override
    public Optional<Bracket> createForTournament(String tournamentId) {
        notNull(tournamentId);

        Optional<Tournament> tournamentOptional = tournamentService.findById(tournamentId);
        //if the Tournament doesn't exist, dont create a new Actor
        if (!tournamentOptional.isPresent()) {
            return Optional.empty();
        }

        Bracket newbracket = new Bracket(tournamentId);
        return Optional.of(bracketRepository.save(newbracket));
    }

    @Override
    public Optional<Bracket> findById(String bracketId) {
        return null;
    }

    @Override
    public Bracket update(Bracket bracket) {
        return null;
    }

    @Override
    public Optional<Bracket> remove(String tournamentId, String bracketId) {
        return null;
    }
}
