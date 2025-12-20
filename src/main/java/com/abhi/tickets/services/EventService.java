package com.abhi.tickets.services;

import com.abhi.tickets.domain.CreateEventRequest;
import com.abhi.tickets.domain.CreateTicketTypeRequest;
import com.abhi.tickets.domain.Event;

import java.util.UUID;

public interface EventService {
    Event createEvent(UUID organizerId, CreateEventRequest event);
}
