package tn.esprit.eventsproject.mapper;

import tn.esprit.eventsproject.dto.ParticipantDTO;
import tn.esprit.eventsproject.entities.Participant;

public class ParticipantMapper {
    public static Participant toEntity(ParticipantDTO dto) {
        Participant p = new Participant();
        p.setNom(dto.getNom());
        p.setPrenom(dto.getPrenom());
        p.setTache(dto.getTache());
        return p;
    }
}
