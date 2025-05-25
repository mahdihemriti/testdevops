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
import tn.esprit.eventsproject.dto.EventDTO;
import tn.esprit.eventsproject.dto.LogisticsDTO;
import tn.esprit.eventsproject.dto.ParticipantDTO;
import tn.esprit.eventsproject.entities.Event;
import tn.esprit.eventsproject.entities.Logistics;
import tn.esprit.eventsproject.entities.Participant;
import tn.esprit.eventsproject.entities.Tache;
import tn.esprit.eventsproject.services.IEventServices;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
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
    @Test
    void testAddEvent() throws Exception {
        EventDTO dto = new EventDTO("Conférence DevOps", LocalDate.of(2025, 6, 1), LocalDate.of(2025, 6, 2));

        Event returned = new Event();
        returned.setDescription("Conférence DevOps");
        returned.setDateDebut(LocalDate.of(2025, 6, 1));
        returned.setDateFin(LocalDate.of(2025, 6, 2));

        Mockito.when(eventServices.addAffectEvenParticipant(Mockito.any(Event.class)))
                .thenReturn(returned);

        mockMvc.perform(post("/event/addEvent")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.description").value("Conférence DevOps"))
                .andExpect(jsonPath("$.dateDebut").value("2025-06-01"))
                .andExpect(jsonPath("$.dateFin").value("2025-06-02"));
    }
    @Test
    void testAddAffectLog() throws Exception {
        LogisticsDTO dto = new LogisticsDTO("Écran", true, 150.0f, 2);

        Logistics saved = new Logistics();
        saved.setDescription("Écran");
        saved.setReserve(true);
        saved.setPrixUnit(150.0f);
        saved.setQuantite(2);

        Mockito.when(eventServices.addAffectLog(Mockito.any(Logistics.class), Mockito.eq("DevFest")))
                .thenReturn(saved);

        mockMvc.perform(put("/event/addAffectLog/DevFest")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.description").value("Écran"))
                .andExpect(jsonPath("$.reserve").value(true))
                .andExpect(jsonPath("$.prixUnit").value(150.0))
                .andExpect(jsonPath("$.quantite").value(2));
    }

}