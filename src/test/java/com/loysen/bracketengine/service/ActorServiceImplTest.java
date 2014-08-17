package com.loysen.bracketengine.service;

import com.loysen.bracketengine.model.Actor;
import com.loysen.bracketengine.model.Tournament;
import com.loysen.bracketengine.repository.ActorRepository;
import com.loysen.bracketengine.service.impl.ActorServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;
import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

/**
 * Created by kielpedia on 8/12/14.
 */
@RunWith(MockitoJUnitRunner.class)
public class ActorServiceImplTest {

    private ActorServiceImpl actorService;

    @Mock
    private ActorRepository actorRepository;
    @Mock
    private TournamentService tournamentService;

    @Mock
    Actor actor1;
    @Mock
    Actor actor2;
    @Mock
    Tournament tournament;


    @Before
    public void setup() {
        actorService = new ActorServiceImpl(actorRepository, tournamentService);
    }

    @Test
    public void findAllByTournament() {
        when(actorRepository.findByTournamentId("test")).thenReturn(new HashSet<Actor>(Arrays.asList(actor1, actor2)));

        Set<Actor> actors = actorService.findAllByTournament("test");

        assertEquals(2, actors.size());
        assertTrue(actors.contains(actor1));
        assertTrue(actors.contains(actor2));
    }


    @Test
    public void findAllByTournament_empty() {
        when(actorRepository.findByTournamentId("test")).thenReturn(new HashSet<Actor>());

        Set<Actor> actors = actorService.findAllByTournament("test");

        assertTrue(actors.isEmpty());
    }

    @Test
    public void createForTournament() {
        Tournament tournament = mock(Tournament.class);
        when(tournamentService.findById("test")).thenReturn(Optional.of(tournament));
        when(actorRepository.save(any(Actor.class))).thenReturn(new Actor("name","test"));

        Optional<Actor> actor = actorService.createForTournament("name", "test");

        assertTrue(actor.isPresent());
        assertEquals("test", actor.get().getTournamentId());
    }

    @Test
    public void createForTournament_nameAlreadyExists() {
        Tournament tournament = mock(Tournament.class);
        when(tournamentService.findById("test")).thenReturn(Optional.of(tournament));
        when(actorRepository.findByNameAndTournamentId("name", "test")).thenReturn(actor1);

        Optional<Actor> actor = actorService.createForTournament("name","test");

        assertFalse(actor.isPresent());
    }

    @Test
    public void createForTournament_tournamentDoesNotExist() {
        Tournament tournament = null;
        when(tournamentService.findById("test")).thenReturn(Optional.ofNullable(tournament));

        Optional<Actor> actor = actorService.createForTournament("name", "test");

        assertFalse(actor.isPresent());
    }

    @Test
    public void update() {
        Actor actor = new Actor("name","tournamentId");

        when(actorRepository.save(any(Actor.class))).thenReturn(actor);

        assertNotNull(actorService.update(actor));
        verify(actorRepository, times(1)).save(actor);
    }

    @Test
    public void remove() {
        when(actor1.getTournamentId()).thenReturn("tournamentId");
        when(tournamentService.findById("tournamentId")).thenReturn(Optional.of(tournament));
        when(actorRepository.findOne("actorId")).thenReturn(actor1);

        Optional<Actor> optional = actorService.remove("tournamentId", "actorId");

        assertTrue(optional.isPresent());
        verify(actorRepository, times(1)).delete(actor1);
    }

    @Test
    public void remove_actorNotFoundOrDoesNotMatch() {
        when(actor1.getTournamentId()).thenReturn("tournamentId1");
        when(actorRepository.findOne("actorId")).thenReturn(null);

        Optional<Actor> optional = actorService.remove("tournamentId", "actorId");

        assertFalse(optional.isPresent());
        verify(actorRepository, never()).delete(actor1);

        when(actorRepository.findOne("actorId")).thenReturn(actor1);
        when(actor1.getTournamentId()).thenReturn("tournamentId1");

        optional = actorService.remove("tournamentId", "actorId");

        assertFalse(optional.isPresent());
        verify(actorRepository, never()).delete(actor1);
    }

}
