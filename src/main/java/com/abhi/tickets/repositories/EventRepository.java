package com.abhi.tickets.repositories;

import com.abhi.tickets.domain.Event;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EventRepository extends JpaRepository<Event, Integer> {
    Page<Event> findByOrganizerId(UUID organizerId, Pageable pageable);
}
