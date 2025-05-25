package tn.esprit.eventsproject.mapper;

import tn.esprit.eventsproject.dto.ParticipantDTO;
import tn.esprit.eventsproject.entities.Participant;

public class ParticipantMapper {
    // Constructeur privé pour empêcher l’instanciation
    private ParticipantMapper() {
        throw new UnsupportedOperationException("Utility class");
    }

    public static Participant toEntity(ParticipantDTO dto) {
        Participant p = new Participant();
        p.setNom(dto.getNom());
        p.setPrenom(dto.getPrenom());
        p.setTache(dto.getTache());
        return p;
    }
}
