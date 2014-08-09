package com.loysen.bracketengine.controller;

import com.loysen.bracketengine.model.Bracket;
import com.loysen.bracketengine.repository.BracketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by kielpedia on 5/31/14.
 */
@Controller
@RequestMapping("/brackets")
public class BracketController {

    @Autowired
    private BracketRepository repository;

    @RequestMapping(value = "/{bracketId}", method = RequestMethod.GET)
    public ResponseEntity<Bracket> findByName(@PathVariable String bracketId) {
        return new ResponseEntity<Bracket>(repository.findOne(bracketId), HttpStatus.OK);
    }

//    @RequestMapping(value = "/{bracketId}", method = RequestMethod.PUT)
//    public ResponseEntity<Bracket> update() {
//        return new ResponseEntity<Bracket>(repository.save(new Bracket(bracketName)), HttpStatus.CREATED);
//    }


}
