package com.loysen.marchmadness.controller;

import com.loysen.marchmadness.model.Actor;
import com.loysen.marchmadness.model.Bracket;
import com.loysen.marchmadness.model.Tournament;
import com.loysen.marchmadness.repository.ActorRepository;
import com.loysen.marchmadness.repository.BracketRepository;
import com.loysen.marchmadness.repository.TournamentRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Set;

/**
 * Created by kielpedia on 6/29/14.
 */
@Controller
@RequestMapping(value = "/tournaments")
public class TournamentController {

    private TournamentRepository tournamentRepository;
    private BracketRepository bracketRepository;
    private ActorRepository actorRepository;

    @Autowired
    public TournamentController(TournamentRepository tournamentRepository, BracketRepository bracketRepository, ActorRepository actorRepository){
        this.tournamentRepository = tournamentRepository;
        this.bracketRepository = bracketRepository;
        this.actorRepository = actorRepository;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Tournament>> findAll() {
        return new ResponseEntity<List<Tournament>>(tournamentRepository.findAll(), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Tournament> create(@RequestParam(value = "tournamentName") String tournamentName) {
        Tournament tournament = tournamentRepository.save(new Tournament(tournamentName));
        return new ResponseEntity<Tournament>(tournament, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{tournamentId}/publish", method = RequestMethod.POST)
    public ResponseEntity<Tournament> publish(@PathVariable ObjectId tournamentId) {
        Tournament tournament = tournamentRepository.findOne(tournamentId.toString());
        tournament.setPublished(true);

        return new ResponseEntity<Tournament>(tournament, HttpStatus.OK);
    }

    // Bracket section

    @RequestMapping(value = "/{tournamentId}/brackets", method = RequestMethod.GET)
    public ResponseEntity<Set<Bracket>> getBrackets(@PathVariable ObjectId tournamentId) {
        return new ResponseEntity<Set<Bracket>>(bracketRepository.findByTournamentId(tournamentId), HttpStatus.OK);
    }

    @RequestMapping(value = "/{tournamentId}/brackets", method = RequestMethod.POST)
    public ResponseEntity<Bracket> addBracket(@PathVariable ObjectId tournamentId,
                                              @RequestParam(value = "bracketName") String bracketName) {
        Bracket bracket = new Bracket(bracketName, tournamentId);
        return new ResponseEntity<Bracket>(bracketRepository.save(bracket), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{tournamentId}/brackets/{bracketId}", method = RequestMethod.DELETE)
    public ResponseEntity<Bracket> deleteBracket(@PathVariable String bracketId) {
        Bracket bracket = bracketRepository.findOne(bracketId);
        bracketRepository.delete(bracket);
        return new ResponseEntity<Bracket>(bracket, HttpStatus.OK);
    }

    // Actor Section

    @RequestMapping(value = "/{tournamentId}/actors", method = RequestMethod.GET)
    public ResponseEntity<Set<Actor>> getActors(@PathVariable ObjectId tournamentId) {
        return new ResponseEntity<Set<Actor>>(actorRepository.findByTournamentId(tournamentId), HttpStatus.OK);
    }

    @RequestMapping(value = "/{tournamentId}/actors", method = RequestMethod.POST)
    public ResponseEntity<Actor> addActor(@PathVariable ObjectId tournamentId,
                                          @RequestParam(value = "actorName") String actorName) {
        Actor actor = new Actor(actorName, tournamentId);

        return new ResponseEntity<Actor>(actorRepository.save(actor), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{tournamentId}/actors/{actorId}", method = RequestMethod.DELETE)
    public ResponseEntity<Actor> deleteActor(@PathVariable String actorId) {
        Actor actor = actorRepository.findOne(actorId);
        actorRepository.delete(actor);
        return new ResponseEntity<Actor>(actor, HttpStatus.OK);
    }
}
