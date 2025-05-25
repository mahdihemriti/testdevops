package tn.esprit.eventsproject;

import org.junit.jupiter.api.Test;
import tn.esprit.eventsproject.dto.LogisticsDTO;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class LogisticsDTOTest {
    @Test
    void testDTOAccessors() {
        LogisticsDTO dto = new LogisticsDTO("Matériel", true, 25.5f, 12);
        assertEquals("Matériel", dto.getDescription());
        assertTrue(dto.isReserve());
        assertEquals(25.5f, dto.getPrixUnit());
        assertEquals(12, dto.getQuantite());
    }
}
