import com.loysen.marchmadness.controller.TournamentController
import com.loysen.marchmadness.model.Tournament
import com.loysen.marchmadness.service.ActorService
import com.loysen.marchmadness.service.BracketService
import com.loysen.marchmadness.service.TournamentService
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import spock.lang.Specification

import java.nio.charset.Charset

import static org.hamcrest.Matchers.hasSize
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
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
        def tournament1 = new Tournament("one");
        tournamentService.findAll() >> Arrays.asList(tournament1)

        when:
        def response = mockMvc.perform(get("/tournaments"))

        then:
        response.andExpect(status().isOk())
                .andExpect(content().contentType(JSON_UTF8))
                .andExpect(jsonPath('$', hasSize(1)))
                .andExpect(jsonPath('$[0].name').value("one"))
    }

    def "Create Tournament with name"() {

    }
}