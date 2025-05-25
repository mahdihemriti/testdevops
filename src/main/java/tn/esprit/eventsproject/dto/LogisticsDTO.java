package tn.esprit.eventsproject.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LogisticsDTO {
    String description;
    boolean reserve;
    float prixUnit;
    int quantite;
}
