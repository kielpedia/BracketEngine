package com.loysen.bracketengine.service.impl;

import com.loysen.bracketengine.exceptions.TournamentNotReadyException;
import com.loysen.bracketengine.model.Tournament;
import com.loysen.bracketengine.repository.TournamentRepository;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

/**
 * Created by kielpedia on 2/7/15.
 */
@RunWith(MockitoJUnitRunner.class)
public class TournamentServiceImplTest {

    @InjectMocks
    private TournamentServiceImpl tournamentService;

    @Mock
    private TournamentRepository tournamentRepository;

    @Mock
    private Tournament tournament;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Before
    public void setup() {
        tournamentService = new TournamentServiceImpl(tournamentRepository);
    }

    @Test
    public void findById() {
        when(tournamentRepository.findOne(anyString())).thenReturn(tournament);

        Optional<Tournament> result = tournamentService.findById("anyString");

        assertTrue(result.isPresent());
        assertEquals(tournament, result.get());
    }

    @Test
    public void findById_notFound() {
        Optional<Tournament> result = tournamentService.findById("anyString");

        assertFalse(result.isPresent());
    }

    @Test
    public void update() {
        Set<String> updatedDivisions = new HashSet<>();
        updatedDivisions.add("one");

        Tournament original = new Tournament();
        original.setId("one");
        original.setName("Name1");
        original.setPublished(false);
        original.setDivisions(Collections.emptySet());

        Tournament update = new Tournament();
        update.setId("two");
        update.setName("Name2");
        update.setPublished(true);
        update.setDivisions(updatedDivisions);

        when(tournamentRepository.findOne(anyString())).thenReturn(original);
        when(tournamentRepository.save(any(Tournament.class))).thenReturn(original);

        Optional<Tournament> result = tournamentService.update(update);

        assertTrue(result.isPresent());

        Tournament updated = result.get();

        assertEquals(original.getId(), updated.getId());
        assertEquals(update.getName(), updated.getName());
        assertEquals(updatedDivisions, updated.getDivisions());
        assertEquals(original.isPublished(), updated.isPublished());
    }

    @Test
    public void update_notFound() {
        Tournament update = new Tournament();
        update.setId("one");

        Optional<Tournament> result = tournamentService.update(update);

        assertFalse(result.isPresent());
    }

    @Test
    public void publish() throws TournamentNotReadyException {
        Tournament original = new Tournament();
        original.setPublished(false);

        when(tournamentRepository.findOne(anyString())).thenReturn(original);
        when(tournamentRepository.save(any(Tournament.class))).thenReturn(original);

        assertTrue(tournamentService.publish("anyString"));

    }

    @Test
    public void publish_notFound() throws TournamentNotReadyException {
        assertFalse(tournamentService.publish("anyString"));
    }

    @Test
    public void remove() {
        when(tournamentRepository.findOne(anyString())).thenReturn(tournament);

        Optional<Tournament> result = tournamentService.remove("anyString");

        verify(tournamentRepository, times(1)).delete(anyString());
        assertTrue(result.isPresent());
        assertEquals(tournament, result.get());
    }

    @Test
    public void remove_notFound() {
        Optional<Tournament> result = tournamentService.remove("anyString");

        assertFalse(result.isPresent());
    }
}
