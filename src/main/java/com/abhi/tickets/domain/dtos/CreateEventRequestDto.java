package com.abhi.tickets.domain.dtos;


import com.abhi.tickets.domain.EventStatusEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateEventRequestDto {

    @NotBlank(message = "Event name is requied")
    private String name;
    private LocalDateTime start;
    private LocalDateTime end;

    @NotBlank(message = "Venue information is required")
    private String venue;
    private LocalDateTime saleStart;
    private LocalDateTime saleEnd;
    @NotNull(message = "Event status must be provided")
    private EventStatusEnum status;
    @NotEmpty(message = "At least one ticket type is available")
    private List<CreateTicketTypeRequestDto> ticketTypes;
}
