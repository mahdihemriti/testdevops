package tn.esprit.eventsproject;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import tn.esprit.eventsproject.controllers.EventRestController;
import tn.esprit.eventsproject.dto.ParticipantDTO;
import tn.esprit.eventsproject.entities.Participant;
import tn.esprit.eventsproject.entities.Tache;
import tn.esprit.eventsproject.services.IEventServices;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(EventRestController.class)
class EventRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IEventServices eventServices;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testAddParticipant() throws Exception {
        ParticipantDTO dto = new ParticipantDTO("Ali", "Ben Ali", Tache.ORGANISATEUR);
        Participant returned = new Participant();
        returned.setIdPart(1);
        returned.setNom("Ali");
        returned.setPrenom("Ben Ali");
        returned.setTache(Tache.ORGANISATEUR);

        Mockito.when(eventServices.addParticipant(Mockito.any())).thenReturn(returned);

        mockMvc.perform(post("/event/addPart")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nom").value("Ali"))
                .andExpect(jsonPath("$.prenom").value("Ben Ali"));
    }
}