package com.abhi.tickets.mappers;

import com.abhi.tickets.domain.CreateEventRequest;
import com.abhi.tickets.domain.CreateTicketTypeRequest;
import com.abhi.tickets.domain.Event;
import com.abhi.tickets.domain.TicketType;
import com.abhi.tickets.domain.dtos.*;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface EventMapper {
    CreateTicketTypeRequest fromDto(CreateTicketTypeRequestDto dto);
    CreateEventRequest fromDto(CreateEventRequestDto dto);
    CreateEventResponseDto toDto(Event event);
    ListEventTicketTypesResponseDto toDto(TicketType ticketType);
    ListEventResponseDto toListEventResponseDto(Event event);
    GetEventTicketTypeResponseDto toGetEventTicketTypeResponseDto(TicketType ticketType);
    GetEventDetailsResponseDto toGetEventDetailsResponseDto(Event event);
}
