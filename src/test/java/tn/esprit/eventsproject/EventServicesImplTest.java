package tn.esprit.eventsproject;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.eventsproject.entities.Event;
import tn.esprit.eventsproject.entities.Logistics;
import tn.esprit.eventsproject.entities.Participant;
import tn.esprit.eventsproject.entities.Tache;
import tn.esprit.eventsproject.repositories.EventRepository;
import tn.esprit.eventsproject.repositories.LogisticsRepository;
import tn.esprit.eventsproject.repositories.ParticipantRepository;
import tn.esprit.eventsproject.services.EventServicesImpl;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EventServicesImplTest {

    @Mock
    private EventRepository eventRepository;

    @Mock
    private ParticipantRepository participantRepository;

    @Mock
    private LogisticsRepository logisticsRepository;

    @InjectMocks
    private EventServicesImpl eventServices;

    private Event event;
    private Participant participant;
    private Logistics logistics;

    @BeforeEach
    void setUp() {
        event = new Event();
        event.setIdEvent(1);
        event.setDescription("Hackathon");

        participant = new Participant();
        participant.setIdPart(1);
        participant.setNom("Tounsi");
        participant.setPrenom("Ahmed");
        participant.setTache(Tache.ORGANISATEUR);

        logistics = new Logistics();
        logistics.setDescription("Tables");
        logistics.setQuantite(10);
        logistics.setPrixUnit(15.0f);
        logistics.setReserve(true);
    }

    @Test
    void testAddParticipant() {
        when(participantRepository.save(participant)).thenReturn(participant);

        Participant saved = eventServices.addParticipant(participant);

        assertNotNull(saved);
        verify(participantRepository).save(participant);
    }

    @Test
    void testAddAffectEvenParticipantWithId() {
        when(participantRepository.findById(1)).thenReturn(Optional.of(participant));
        when(eventRepository.save(event)).thenReturn(event);

        Event result = eventServices.addAffectEvenParticipant(event, 1);

        assertNotNull(result);
        assertTrue(participant.getEvents().contains(event));
        verify(eventRepository).save(event);
    }

    @Test
    void testAddAffectEvenParticipantWithParticipantsList() {
        Set<Participant> participants = new HashSet<>();
        participants.add(participant);
        event.setParticipants(participants);

        when(participantRepository.findById(1)).thenReturn(Optional.of(participant));
        when(eventRepository.save(event)).thenReturn(event);

        participant.setIdPart(1); // Ensure ID is set

        Event result = eventServices.addAffectEvenParticipant(event);

        assertNotNull(result);
        assertTrue(participant.getEvents().contains(event));
        verify(eventRepository).save(event);
    }

    @Test
    void testAddAffectLog() {
        when(eventRepository.findByDescription("Hackathon")).thenReturn(event);
        when(logisticsRepository.save(logistics)).thenReturn(logistics);

        Logistics saved = eventServices.addAffectLog(logistics, "Hackathon");

        assertNotNull(saved);
        assertTrue(event.getLogistics().contains(logistics));
        verify(logisticsRepository).save(logistics);
    }

    @Test
    void testGetLogisticsDates() {
        Event e = new Event();
        e.setLogistics(new HashSet<>(Arrays.asList(logistics)));
        when(eventRepository.findByDateDebutBetween(any(), any())).thenReturn(Arrays.asList(e));

        List<Logistics> result = eventServices.getLogisticsDates(LocalDate.now().minusDays(1), LocalDate.now().plusDays(1));

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Tables", result.get(0).getDescription());
    }

    @Test
    void testCalculCout() {
        event.setDescription("Hackathon");
        event.setLogistics(new HashSet<>(Arrays.asList(logistics)));
        when(eventRepository.findByParticipants_NomAndParticipants_PrenomAndParticipants_Tache(
                "Tounsi", "Ahmed", Tache.ORGANISATEUR))
                .thenReturn(Arrays.asList(event));

        eventServices.calculCout();

        assertEquals(150.0f, event.getCout());
        verify(eventRepository).save(event);
    }
}
