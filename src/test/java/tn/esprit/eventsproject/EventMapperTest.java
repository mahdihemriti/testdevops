package tn.esprit.eventsproject;

import org.junit.jupiter.api.Test;
import tn.esprit.eventsproject.dto.EventDTO;
import tn.esprit.eventsproject.entities.Event;
import tn.esprit.eventsproject.mapper.EventMapper;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class EventMapperTest {
    @Test
    void testToEntity() {
        LocalDate start = LocalDate.of(2025, 5, 1);
        LocalDate end = LocalDate.of(2025, 5, 3);
        EventDTO dto = new EventDTO("Conférence", start, end);

        Event entity = EventMapper.toEntity(dto);

        assertEquals("Conférence", entity.getDescription());
        assertEquals(start, entity.getDateDebut());
        assertEquals(end, entity.getDateFin());
    }
    @Test
    void testToEntityWithEmptyDTO() {
        EventDTO dto = new EventDTO();
        Event entity = EventMapper.toEntity(dto);
        assertNull(entity.getDescription());
    }
}
