package com.loysen.marchmadness.model;

import org.bson.types.ObjectId;

import java.util.Set;

/**
 * Created by kielpedia on 6/28/14.
 */
public class Match {

    private int round;
    private Set<MatchActor> actors;
    private ObjectId winningActor;


    public class MatchActor {
        private int position;
        private ObjectId actorId;
    }

}
