package com.abhi.tickets.domain.dtos;

import com.abhi.tickets.domain.EventStatusEnum;
import com.abhi.tickets.domain.Ticket;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateEventResponseDto {
    private UUID id;
    private String name;
    private LocalDateTime start;
    private LocalDateTime end;
    private String Venue;
    private String saleStart;
    private String saleEnd;
    private EventStatusEnum status;
    private List<CreateTicketTypeResponseDto> ticketTypes;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
