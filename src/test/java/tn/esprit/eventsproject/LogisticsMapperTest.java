package tn.esprit.eventsproject;

import org.junit.jupiter.api.Test;
import tn.esprit.eventsproject.dto.LogisticsDTO;
import tn.esprit.eventsproject.entities.Logistics;
import tn.esprit.eventsproject.mapper.LogisticsMapper;

import static org.junit.jupiter.api.Assertions.*;

class LogisticsMapperTest {
    @Test
    void testToEntity() {
        LogisticsDTO dto = new LogisticsDTO("Chaises", true, 50.0f, 5);
        Logistics entity = LogisticsMapper.toEntity(dto);

        assertEquals("Chaises", entity.getDescription());
        assertTrue(entity.isReserve());
        assertEquals(50.0f, entity.getPrixUnit());
        assertEquals(5, entity.getQuantite());
    }

    @Test
    void testToDto() {
        Logistics entity = new Logistics();
        entity.setDescription("Tables");
        entity.setReserve(true);
        entity.setPrixUnit(100.0f);
        entity.setQuantite(10);

        LogisticsDTO dto = LogisticsMapper.toDto(entity);

        assertEquals("Tables", dto.getDescription());
        assertTrue(dto.isReserve());
        assertEquals(100.0f, dto.getPrixUnit());
        assertEquals(10, dto.getQuantite());
    }

    @Test
    void testToEntityWithEmptyDTO() {
        LogisticsDTO dto = new LogisticsDTO();
        Logistics entity = LogisticsMapper.toEntity(dto);

        assertNull(entity.getDescription());
        assertFalse(entity.isReserve());
        assertEquals(0.0f, entity.getPrixUnit());
        assertEquals(0, entity.getQuantite());
    }

}
