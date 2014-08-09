package com.loysen.marchmadness.controller;

import com.loysen.marchmadness.exceptions.TournamentNotReadyException;
import com.loysen.marchmadness.model.Actor;
import com.loysen.marchmadness.model.Bracket;
import com.loysen.marchmadness.model.Tournament;
import com.loysen.marchmadness.service.ActorService;
import com.loysen.marchmadness.service.BracketService;
import com.loysen.marchmadness.service.TournamentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
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
        return new ResponseEntity<List<Tournament>>(tournamentService.findAll(), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Tournament> create() {
        Tournament tournament = tournamentService.create();
        return new ResponseEntity<Tournament>(tournament, HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<Tournament> update() {
        return null;
    }

    @RequestMapping(value = "/{tournamentId}/publish", method = RequestMethod.POST)
    public ResponseEntity<Tournament> publish(@PathVariable String tournamentId) throws TournamentNotReadyException {
        Optional<Tournament> tournament = tournamentService.publish(tournamentId);
        if (!tournament.isPresent()) {
            return new ResponseEntity<Tournament>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Tournament>(tournament.get(), HttpStatus.OK);
    }

    // Bracket section

    @RequestMapping(value = "/{tournamentId}/brackets", method = RequestMethod.GET)
    public ResponseEntity<Set<Bracket>> getBrackets(@PathVariable String tournamentId) {
        return new ResponseEntity<Set<Bracket>>(bracketService.findAllByTournament(tournamentId), HttpStatus.OK);
    }

    @RequestMapping(value = "/{tournamentId}/brackets", method = RequestMethod.POST)
    public ResponseEntity<Bracket> addBracket(@PathVariable String tournamentId) {
        Optional<Bracket> bracket = bracketService.createForTournament(tournamentId);

        if (!bracket.isPresent()) {
            return new ResponseEntity<Bracket>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Bracket>(bracket.get(), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{tournamentId}/brackets/{bracketId}", method = RequestMethod.DELETE)
    public ResponseEntity<Bracket> removeBracket(@PathVariable String tournamentId, @PathVariable String bracketId) {

        Optional<Bracket> bracket = bracketService.remove(tournamentId, bracketId);

        if (!bracket.isPresent()) {
            return new ResponseEntity<Bracket>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Bracket>(bracket.get(), HttpStatus.OK);
    }

    // Actor Section

    @RequestMapping(value = "/{tournamentId}/actors", method = RequestMethod.GET)
    public ResponseEntity<Set<Actor>> getActors(@PathVariable String tournamentId) {
        return new ResponseEntity<Set<Actor>>(actorService.findAllByTournament(tournamentId), HttpStatus.OK);
    }

    @RequestMapping(value = "/{tournamentId}/actors", method = RequestMethod.POST)
    public ResponseEntity<Actor> addActor(@PathVariable String tournamentId) {
        Optional<Actor> actor = actorService.createForTournament(tournamentId);

        if (!actor.isPresent()) {
            return new ResponseEntity<Actor>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Actor>(actor.get(), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{tournamentId}/actors/{actorId}", method = RequestMethod.DELETE)
    public ResponseEntity<Actor> removeActor(@PathVariable String tournamentId, @PathVariable String actorId) {
        Optional<Actor> actor = actorService.remove(tournamentId, actorId);

        if (!actor.isPresent()) {
            return new ResponseEntity<Actor>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Actor>(actor.get(), HttpStatus.OK);
    }
}
