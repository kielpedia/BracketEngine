package com.loysen.bracketengine.controller;

import com.loysen.bracketengine.exceptions.TournamentNotReadyException;
import com.loysen.bracketengine.model.Actor;
import com.loysen.bracketengine.model.Bracket;
import com.loysen.bracketengine.model.Tournament;
import com.loysen.bracketengine.service.ActorService;
import com.loysen.bracketengine.service.BracketService;
import com.loysen.bracketengine.service.TournamentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * Created by kielpedia on 6/29/14.
 */
@Controller
@RequestMapping(value = "/tournaments")
public class TournamentController {

    private TournamentService tournamentService;
    private BracketService bracketService;
    private ActorService actorService;

    @Autowired
    public TournamentController(TournamentService tournamentService, BracketService bracketService, ActorService actorService) {
        this.tournamentService = tournamentService;
        this.bracketService = bracketService;
        this.actorService = actorService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Tournament>> findAll() {
        return new ResponseEntity<>(tournamentService.findAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "/{tournamentId}", method = RequestMethod.GET)
    public ResponseEntity<Tournament> findOne(@PathVariable String tournamentId) {
        Optional<Tournament> tournament = tournamentService.findById(tournamentId);

        if (tournament.isPresent()) {
            return new ResponseEntity<>(tournament.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Tournament> create() {
        Tournament tournament = tournamentService.create();
        return new ResponseEntity<>(tournament, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{tournamentId}", method = RequestMethod.PUT)
    public ResponseEntity<Tournament> update(@PathVariable String tournamentId, @RequestBody Tournament tournament) {
        if (!tournamentId.equals(tournament.getId().toString())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Optional<Tournament> updated = tournamentService.update(tournament);

        if (updated.isPresent()) {
            return new ResponseEntity<>(updated.get(), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/{tournamentId}/publish", method = RequestMethod.POST)
    public ResponseEntity<Tournament> publish(@PathVariable String tournamentId) throws TournamentNotReadyException {
        Optional<Tournament> tournament = tournamentService.publish(tournamentId);
        if (!tournament.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(tournament.get(), HttpStatus.OK);
    }

    @RequestMapping(value = "/{tournamentId}", method = RequestMethod.DELETE)
    public ResponseEntity<Tournament> delete(@PathVariable String tournamentId) throws TournamentNotReadyException {
        Optional<Tournament> tournament = tournamentService.remove(tournamentId);
        if (!tournament.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(tournament.get(), HttpStatus.OK);
    }

    // Bracket section

    @RequestMapping(value = "/{tournamentId}/brackets", method = RequestMethod.GET)
    public ResponseEntity<Set<Bracket>> getBrackets(@PathVariable String tournamentId) {
        return new ResponseEntity<>(bracketService.findAllByTournament(tournamentId), HttpStatus.OK);
    }

    @RequestMapping(value = "/{tournamentId}/brackets", method = RequestMethod.POST)
    public ResponseEntity<Bracket> addBracket(@PathVariable String tournamentId) {
        Optional<Bracket> bracket = bracketService.createForTournament(tournamentId);

        if (!bracket.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(bracket.get(), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{tournamentId}/brackets/{bracketId}", method = RequestMethod.DELETE)
    public ResponseEntity<Bracket> removeBracket(@PathVariable String tournamentId, @PathVariable String bracketId) {

        Optional<Bracket> bracket = bracketService.remove(tournamentId, bracketId);

        if (!bracket.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(bracket.get(), HttpStatus.OK);
    }

    // Actor Section

    @RequestMapping(value = "/{tournamentId}/actors", method = RequestMethod.GET)
    public ResponseEntity<Set<Actor>> getActors(@PathVariable String tournamentId) {
        return new ResponseEntity<>(actorService.findAllByTournament(tournamentId), HttpStatus.OK);
    }

    @RequestMapping(value = "/{tournamentId}/actors", method = RequestMethod.POST)
    public ResponseEntity<Actor> addActor(@PathVariable String tournamentId) {
        Optional<Actor> actor = actorService.createForTournament(tournamentId);

        if (!actor.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(actor.get(), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{tournamentId}/actors/{actorId}", method = RequestMethod.DELETE)
    public ResponseEntity<Actor> removeActor(@PathVariable String tournamentId, @PathVariable String actorId) {
        Optional<Actor> actor = actorService.remove(tournamentId, actorId);

        if (!actor.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(actor.get(), HttpStatus.OK);
    }
}
