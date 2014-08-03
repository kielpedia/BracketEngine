package com.loysen.marchmadness.service;

import com.loysen.marchmadness.model.Bracket;

/**
 * Created by kielpedia on 6/29/14.
 */
public interface BracketService {

    Bracket create(String tournamentId);

    Bracket delete(String tournamentId);

    Bracket update(Bracket bracket);


}
