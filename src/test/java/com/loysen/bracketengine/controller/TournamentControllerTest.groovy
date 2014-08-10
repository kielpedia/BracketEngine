import com.fasterxml.jackson.databind.ObjectMapper
import com.loysen.bracketengine.controller.TournamentController
import com.loysen.bracketengine.model.Tournament
import com.loysen.bracketengine.service.ActorService
import com.loysen.bracketengine.service.BracketService
import com.loysen.bracketengine.service.TournamentService
import org.bson.types.ObjectId
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import spock.lang.Specification

import java.nio.charset.Charset

import static org.hamcrest.Matchers.hasSize
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup

class TournamentControllerTest extends Specification {

    private static final MediaType JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"))

    MockMvc mockMvc

    TournamentService tournamentService = Mock()
    BracketService bracketService = Mock()
    ActorService actorService = Mock()

    def setup() {
        TournamentController controller = new TournamentController(tournamentService, bracketService, actorService);

        mockMvc = standaloneSetup(controller).build();
    }

    def "Find All Tournaments"() {
        given:
        def tournament1 = new Tournament();
        tournament1.name = "one"
        tournamentService.findAll() >> Arrays.asList(tournament1)

        when:
        def response = mockMvc.perform(get("/tournaments"))

        then:
        response.andExpect(status().isOk())
                .andExpect(content().contentType(JSON_UTF8))
                .andExpect(jsonPath('$', hasSize(1)))
                .andExpect(jsonPath('$[0].name').value("one"))
    }

    def "Create Tournament"() {
        given:
        def tournament1 = new Tournament();
        tournament1.name = "one"
        tournamentService.create() >> tournament1

        when:
        def response = mockMvc.perform(post("/tournaments"))

        then:
        response.andExpect(status().isCreated())
                .andExpect(content().contentType(JSON_UTF8))
                .andExpect(jsonPath('$name').value("one"))
    }

    def "Update Tournament"() {
        given:
        def tournament1 = new Tournament();
        tournament1.name = "one"
        tournament1.id = new ObjectId();
        tournament1.divisions = new HashSet<>();
        String json = new ObjectMapper().writer().writeValueAsString(tournament1);
        tournamentService.update(_) >> tournament1

        when:
        def response = mockMvc.perform(put("/tournaments").content(json)
                .contentType(MediaType.APPLICATION_JSON))

        then:
        response.andExpect(status().isOk())
                .andExpect(content().contentType(JSON_UTF8))
                .andExpect(jsonPath('$name').value("one"))
    }

    def "Publish Tournament"() {
        given:
        def tournament1 = new Tournament()
        tournament1.name = "one";
        def Optional<Tournament> optional = new Optional<>(tournament1);
        tournamentService.publish("1") >> optional

        when:
        def response = mockMvc.perform(post("/tournaments/1/publish"))

        then:
        response.andExpect(status().isOk())
                .andExpect(content().contentType(JSON_UTF8))
                .andExpect(jsonPath('$name').value("one"))


    }

    def "Publish Tournament with no matching ID"() {
        given:
        def Optional<Tournament> optional = new Optional<>();
        tournamentService.publish("1") >> optional

        when:
        def response = mockMvc.perform(post("/tournaments/1/publish"))

        then:
        response.andExpect(status().isNotFound())
    }
}