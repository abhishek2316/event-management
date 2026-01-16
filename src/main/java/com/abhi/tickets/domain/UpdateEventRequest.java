package com.abhi.tickets.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateEventRequest {
    private String name;
    private LocalDateTime start;
    private LocalDateTime end;
    private String venue;
    private LocalDateTime saleStart;
    private LocalDateTime saleEnd;
    private EventStatusEnum status;
    private List<UpdateTicketTypeRequest> ticketTypes = new ArrayList<>();
}
