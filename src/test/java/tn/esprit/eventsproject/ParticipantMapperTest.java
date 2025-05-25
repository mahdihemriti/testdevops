package tn.esprit.eventsproject;

import org.junit.jupiter.api.Test;
import tn.esprit.eventsproject.dto.ParticipantDTO;
import tn.esprit.eventsproject.entities.Participant;
import tn.esprit.eventsproject.entities.Tache;
import tn.esprit.eventsproject.mapper.ParticipantMapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class ParticipantMapperTest {
    @Test
    void testToEntity() {
        ParticipantDTO dto = new ParticipantDTO("Ali", "Ben Ali", Tache.ORGANISATEUR);
        Participant entity = ParticipantMapper.toEntity(dto);

        assertEquals("Ali", entity.getNom());
        assertEquals("Ben Ali", entity.getPrenom());
        assertEquals(Tache.ORGANISATEUR, entity.getTache());
    }
    @Test
    void testToEntityWithEmptyDTO() {
        ParticipantDTO dto = new ParticipantDTO();
        Participant entity = ParticipantMapper.toEntity(dto);

        assertNull(entity.getNom());
        assertNull(entity.getPrenom());
        assertNull(entity.getTache());
    }
}
