package com.abhi.tickets.controllers;

import com.abhi.tickets.domain.CreateEventRequest;
import com.abhi.tickets.domain.Event;
import com.abhi.tickets.domain.dtos.CreateEventRequestDto;
import com.abhi.tickets.domain.dtos.CreateEventResponseDto;
import com.abhi.tickets.domain.dtos.GetEventDetailsResponseDto;
import com.abhi.tickets.domain.dtos.ListEventResponseDto;
import com.abhi.tickets.mappers.EventMapper;
import com.abhi.tickets.services.EventService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping(path = "/api/v1/events")
@RequiredArgsConstructor
public class EventController {

    private final EventMapper eventMapper;
    private final EventService eventService;

    @PostMapping
    public ResponseEntity<CreateEventResponseDto> createEvent(
            @AuthenticationPrincipal Jwt jwt, @Valid @RequestBody CreateEventRequestDto createEventRequestDto) {
        CreateEventRequest createEventRequest = eventMapper.fromDto(createEventRequestDto);
//        UUID userId = UUID.fromString(jwt.getSubject());
        UUID userId = parseUUID(jwt);
        Event createEvent = eventService.createEvent(userId, createEventRequest);
        CreateEventResponseDto createEventResponseDto = eventMapper.toDto(createEvent);
        return new  ResponseEntity<>(createEventResponseDto, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Page< ListEventResponseDto>> listEvents(
            @AuthenticationPrincipal Jwt jwt, Pageable pageable
    ){
        UUID userId = parseUUID(jwt);
        Page<Event> events =  eventService.listEventsForOrganizer(userId, pageable );
        return ResponseEntity.ok(events.map(eventMapper::toListEventResponseDto));
    }

    @GetMapping(path = "/{eventId}")
    public ResponseEntity<GetEventDetailsResponseDto> getEvent(
            @AuthenticationPrincipal Jwt jwt, @PathVariable UUID eventId
    ) {
        UUID userId = parseUUID(jwt);
        return eventService.getEventForOrganizer(userId, eventId).map(eventMapper::toGetEventDetailsResponseDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


    private UUID parseUUID(Jwt jwt) {
        return UUID.fromString(jwt.getSubject());
    }
}
