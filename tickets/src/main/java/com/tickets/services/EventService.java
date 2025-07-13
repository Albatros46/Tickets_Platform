package com.tickets.services;

import com.tickets.model.Event;
import com.tickets.request.CreateEventRequest;

import java.util.UUID;

public interface EventService {
    Event createEvent(UUID organizerId, CreateEventRequest event);
}
