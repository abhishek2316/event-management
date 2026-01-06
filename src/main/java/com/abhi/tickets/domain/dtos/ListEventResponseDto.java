package com.abhi.tickets.domain.dtos;


import com.abhi.tickets.domain.EventStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListEventResponseDto {
    private UUID id;
    private String name;
    private LocalDateTime start;
    private LocalDateTime end;
    private String Venue;
    private LocalDateTime sale_start;
    private LocalDateTime sale_end;
    private EventStatusEnum status;
    private List<ListEventTicketTypesResponseDto>  ticket_types = new ArrayList<>();
}
