package tn.esprit.eventsproject.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.eventsproject.dto.EventDTO;
import tn.esprit.eventsproject.dto.ParticipantDTO;
import tn.esprit.eventsproject.entities.Event;
import tn.esprit.eventsproject.entities.Logistics;
import tn.esprit.eventsproject.entities.Participant;
import tn.esprit.eventsproject.mapper.EventMapper;
import tn.esprit.eventsproject.mapper.ParticipantMapper;
import tn.esprit.eventsproject.services.IEventServices;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping("event")
@RestController
public class EventRestController {
    private final IEventServices eventServices;

    @PostMapping("/addPart")
    public Participant addParticipant(@RequestBody ParticipantDTO dto){
        return eventServices.addParticipant(ParticipantMapper.toEntity(dto));
    }
    @PostMapping("/addEvent/{id}")
    public Event addEventPart(@RequestBody EventDTO dto, @PathVariable("id") int idPart){
        return eventServices.addAffectEvenParticipant(EventMapper.toEntity(dto), idPart);
    }
    @PostMapping("/addEvent")
    public Event addEvent(@RequestBody EventDTO dto){
        return eventServices.addAffectEvenParticipant(EventMapper.toEntity(dto));
    }
    @PutMapping("/addAffectLog/{description}")
    public Logistics addAffectLog(@RequestBody Logistics logistics,@PathVariable("description") String descriptionEvent){
        return eventServices.addAffectLog(logistics,descriptionEvent);
    }
    @GetMapping("/getLogs/{d1}/{d2}")
    public List<Logistics> getLogistiquesDates (@PathVariable("d1") LocalDate date_debut, @PathVariable("d2") LocalDate date_fin){
        return eventServices.getLogisticsDates(date_debut,date_fin);
    }
}
