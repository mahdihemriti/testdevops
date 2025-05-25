package tn.esprit.eventsproject.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;
import tn.esprit.eventsproject.entities.Tache;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ParticipantDTO {
    String nom;
    String prenom;
    Tache tache;
}