package tn.esprit.eventsproject;

import org.junit.jupiter.api.Test;
import tn.esprit.eventsproject.dto.EventDTO;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class EventDTOTest {
    @Test
    void testDTOAccessors() {
        EventDTO dto = new EventDTO("Hackathon", LocalDate.now(), LocalDate.now().plusDays(1));
        assertNotNull(dto.getDescription());
        assertNotNull(dto.getDateDebut());
        assertNotNull(dto.getDateFin());
    }
}
