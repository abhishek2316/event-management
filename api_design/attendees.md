## Search Published Events
GET /api/v1/published-events

## Retrieve Published Event
GET /api/v1/published-event/{published_event_id}

## Purchase Ticket
POST /api/v1/published-event/{published_event_id}/ticket-types/{ticket_types_id}
## List Tickets (for user)
GET /api/v1/tickets
## Retrieve Ticket (for user)
GET /api/v1/tickets/{ticket_id}
## Retrieve Ticket QR Code
GET /api/v1/tickets/{ticket_id}/qr-codes 