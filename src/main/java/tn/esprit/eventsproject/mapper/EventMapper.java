package tn.esprit.eventsproject.mapper;

import tn.esprit.eventsproject.dto.EventDTO;
import tn.esprit.eventsproject.entities.Event;

public class EventMapper {
    // Empêche l'instanciation de la classe
    private EventMapper() {
        throw new UnsupportedOperationException("Utility class");
    }

    public static Event toEntity(EventDTO dto) {
        Event e = new Event();
        e.setDescription(dto.getDescription());
        e.setDateDebut(dto.getDateDebut());
        e.setDateFin(dto.getDateFin());
        return e;
    }
}
