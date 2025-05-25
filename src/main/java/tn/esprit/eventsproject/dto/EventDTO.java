package tn.esprit.eventsproject.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EventDTO {
    String description;
    LocalDate dateDebut;
    LocalDate dateFin;
}