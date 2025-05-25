package tn.esprit.eventsproject.mapper;

import tn.esprit.eventsproject.dto.LogisticsDTO;
import tn.esprit.eventsproject.entities.Logistics;

public class LogisticsMapper {
    private LogisticsMapper() {
        throw new UnsupportedOperationException("Utility class");
    }

    public static Logistics toEntity(LogisticsDTO dto) {
        Logistics logistics = new Logistics();
        logistics.setDescription(dto.getDescription());
        logistics.setReserve(dto.isReserve());
        logistics.setPrixUnit(dto.getPrixUnit());
        logistics.setQuantite(dto.getQuantite());
        return logistics;
    }

    public static LogisticsDTO toDto(Logistics logistics) {
        return new LogisticsDTO(
                logistics.getDescription(),
                logistics.isReserve(),
                logistics.getPrixUnit(),
                logistics.getQuantite()
        );
    }
}
