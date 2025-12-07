## Create Event
POST /api/v1/events
Request Body: Event
## List Events
GET /api/v1/events
## Retrieve Event
GET /api/v1/events/{event_id}
## Update Event
PUT /api/v1/events/{event_id}
Request Body: Event
## Delete Event
DELETE /api/v1/events/{event_id}
## List Ticket Sales
GET /api/v1/events/{event_id}/tickets
## Retrieve Ticket Sale
GET /api/v1/events/{event_id}/tickets/tickets/{ticket_id}
## Partial Update
PATCH /api/v1/events/{event_id}/tickets
Request Body: Partial Event
## List Ticket Type
GET /api/v1/events/{event_id}/ticket-types
## Retrieve Ticket Type
GET /api/v1/events/{event_id}/ticket-types/{ticket_type_id}
## Delete Ticket Type
DELETE /api/v1/events/{event_id}/ticket-types/{ticket_type_id}
## Partial Update Ticket Type
PATCH GET /api/v1/events/{event_id}/ticket-types/{ticket_type_id}
Request Body: Partial Ticket Type
## TODO: Dedicated endpoint for report data