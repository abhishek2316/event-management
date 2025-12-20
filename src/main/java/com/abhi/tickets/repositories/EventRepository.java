package com.abhi.tickets.repositories;

import com.abhi.tickets.domain.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Integer> {
}
