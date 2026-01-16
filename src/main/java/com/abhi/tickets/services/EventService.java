package com.abhi.tickets.services;

import com.abhi.tickets.domain.CreateEventRequest;
import com.abhi.tickets.domain.CreateTicketTypeRequest;
import com.abhi.tickets.domain.Event;
import com.abhi.tickets.domain.UpdateEventRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.UUID;

public interface EventService {
    Event createEvent(UUID organizerId, CreateEventRequest event);
    Page<Event> listEventsForOrganizer(UUID organizerId,  Pageable pageable);
    Optional<Event> getEventForOrganizer(UUID organizerId, UUID id);
    Event updateEventForOrganizer(UUID organizerId, UUID id, UpdateEventRequest event);
}
