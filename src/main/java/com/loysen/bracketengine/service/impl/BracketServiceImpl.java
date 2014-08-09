package com.loysen.bracketengine.service.impl;

import com.loysen.bracketengine.model.Bracket;
import com.loysen.bracketengine.repository.BracketRepository;
import com.loysen.bracketengine.service.BracketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

/**
 * Created by kielpedia on 8/8/14.
 */
@Service
public class BracketServiceImpl implements BracketService {

    private BracketRepository bracketRepository;

    @Autowired
    public BracketServiceImpl(BracketRepository bracketRepository) {
        this.bracketRepository = bracketRepository;
    }

    @Override
    public Set<Bracket> findAllByTournament(String tournamentId) {
        return null;
    }

    @Override
    public Optional<Bracket> createForTournament(String tournamentId) {
        return null;
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
