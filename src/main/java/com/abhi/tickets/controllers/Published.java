package com.abhi.tickets.controllers;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/published-events")
public class Published {

    private final PublishedEventService service;

    public <PublishedEventService> Published(PublishedEventService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<Page<PublishedEventSummary>> listPublishedEvents(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "4") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        Page<PublishedEventSummary> result =
                service.listPublishedEvents(pageable);

        return ResponseEntity.ok(result);
    }
}

