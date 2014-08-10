package com.loysen.bracketengine.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.loysen.bracketengine.model.Tournament;
import com.loysen.bracketengine.service.ActorService;
import com.loysen.bracketengine.service.BracketService;
import com.loysen.bracketengine.service.TournamentService;
import org.bson.types.ObjectId;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

/**
 * Created by kielpedia on 8/10/14.
 */
@RunWith(MockitoJUnitRunner.class)
public class TournamentControllerTest {

    private static final MediaType JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));

    private MockMvc mockMvc;

    @Mock
    TournamentService tournamentService;
    @Mock
    BracketService bracketService;
    @Mock
    ActorService actorService;

    @Before
    public void setup() {
        TournamentController controller = new TournamentController(tournamentService, bracketService, actorService);

        mockMvc = standaloneSetup(controller).build();
    }

    @Test
    public void findAll() throws Exception {
        Tournament tournament1 = new Tournament();
        tournament1.setName("one");
        when(tournamentService.findAll()).thenReturn(Arrays.asList(tournament1));

        mockMvc.perform(get("/tournaments"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(JSON_UTF8))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].name").value("one"));
    }

    @Test
    public void create() throws Exception {
        Tournament tournament1 = new Tournament();
        tournament1.setName("one");
        when(tournamentService.create()).thenReturn(tournament1);

        mockMvc.perform(post("/tournaments"))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(JSON_UTF8))
                .andExpect(jsonPath("$name").value("one"));
    }

    @Test
    public void update() throws Exception {
        Tournament tournament1 = new Tournament();
        tournament1.setName("one");
        tournament1.setId(new ObjectId());
        tournament1.setDivisions(new HashSet<String>());
        String json = new ObjectMapper().writer().writeValueAsString(tournament1);
        when(tournamentService.update(any(Tournament.class))).thenReturn(tournament1);

        mockMvc.perform(put("/tournaments").content(json)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(JSON_UTF8))
                .andExpect(jsonPath("$name").value("one"));
    }

    @Test
    public void publish() throws Exception {
        Tournament tournament1 = new Tournament();
        tournament1.setName("one");
        Optional<Tournament> optional = Optional.of(tournament1);
        when(tournamentService.publish("1")).thenReturn(optional);

        mockMvc.perform(post("/tournaments/1/publish"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(JSON_UTF8))
                .andExpect(jsonPath("$name").value("one"));


    }

    @Test
    public void publish_noMatchingId() throws Exception {
        Optional<Tournament> optional = Optional.empty();
        when(tournamentService.publish("1")).thenReturn(optional);

        mockMvc.perform(post("/tournaments/1/publish"))
                .andExpect(status().isNotFound());
    }
}
