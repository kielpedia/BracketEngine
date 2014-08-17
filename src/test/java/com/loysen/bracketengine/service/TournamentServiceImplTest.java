package com.loysen.bracketengine.service;

import com.loysen.bracketengine.model.Tournament;
import com.loysen.bracketengine.repository.TournamentRepository;
import com.loysen.bracketengine.service.impl.TournamentServiceImpl;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

/**
 * Created by kielpedia on 8/10/14.
 */
@RunWith(MockitoJUnitRunner.class)
public class TournamentServiceImplTest {

    private TournamentServiceImpl tournamentService;
    @Mock
    TournamentRepository tournamentRepository;

    @Mock
    Tournament tournament1;
    @Mock
    Tournament tournament2;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Before
    public void setup() {
        tournamentService = new TournamentServiceImpl(tournamentRepository);

    }

    @Test
    public void findAll() {
        when(tournamentRepository.findAll()).thenReturn(Arrays.asList(tournament1, tournament2));

        List<Tournament> tournaments = tournamentService.findAll();
        assertEquals(2, tournaments.size());
        assertTrue(tournaments.contains(tournament1));
        assertTrue(tournaments.contains(tournament2));
    }

    @Test
    public void findAll_empty() {
        when(tournamentRepository.findAll()).thenReturn(Collections.EMPTY_LIST);

        List<Tournament> tournaments = tournamentService.findAll();
        assertTrue(tournaments.isEmpty());
    }

    @Test
    public void findById() {
        when(tournamentRepository.findOne(anyString())).thenReturn(tournament1);

        Optional tournament = tournamentService.findById("test");

        assertTrue(tournament.isPresent());
        assertEquals(tournament1, tournament.get());
    }

    @Test
    public void findById_notPresent() {
        when(tournamentRepository.findOne(anyString())).thenReturn(null);

        Optional tournament = tournamentService.findById("test");

        assertFalse(tournament.isPresent());
    }

    @Test
    public void create() {
        when(tournamentRepository.save(any(Tournament.class))).thenReturn(tournament1);
        assertNotNull(tournamentService.create());
        verify(tournamentRepository, times(1)).save(any(Tournament.class));
    }

    @Test
    public void update() {
        Tournament tournament = new Tournament();

        when(tournamentRepository.save(any(Tournament.class))).thenReturn(tournament);

        assertNotNull(tournamentService.update(tournament));
        verify(tournamentRepository, times(1)).save(tournament);
    }

    @Test
    public void publish() {
        Tournament tournament = new Tournament();
        tournament.setPublished(false);
        tournament.setActivationDate(LocalDateTime.MAX);

        when(tournamentRepository.findOne("test")).thenReturn(tournament);

        assertTrue(tournamentService.publish("test"));
        verify(tournamentRepository, times(1)).save(tournament);
    }

    @Test
    public void publish_tournamentDoesntExist() {

        when(tournamentRepository.findOne("test")).thenReturn(null);

        assertFalse(tournamentService.publish("test"));
    }

    @Test
    public void publish_activationDateInPast() {
        Tournament tournament = new Tournament();
        tournament.setPublished(true);
        tournament.setActivationDate(LocalDateTime.MAX);

        when(tournamentRepository.findOne("test")).thenReturn(tournament);


        assertFalse(tournamentService.publish("test"));
    }

    @Test
    public void publish_alreadyPublished() {
        Tournament tournament = new Tournament();
        tournament.setPublished(false);
        tournament.setActivationDate(LocalDateTime.MIN);

        when(tournamentRepository.findOne("test")).thenReturn(tournament);

        assertFalse(tournamentService.publish("test"));
    }

    @Test
    public void remove() {
        when(tournamentRepository.findOne("test")).thenReturn(tournament1);

        Optional optional = tournamentService.remove("test");

        assertTrue(optional.isPresent());
        verify(tournamentRepository, times(1)).delete("test");
    }

    @Test
    public void remove_tournamentDoesntExist() {
        when(tournamentRepository.findOne("test")).thenReturn(null);

        Optional optional = tournamentService.remove("test");

        assertFalse(optional.isPresent());
        verify(tournamentRepository, never()).delete("test");
    }

}
