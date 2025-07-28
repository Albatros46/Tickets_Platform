package com.tickets.services.impl;

import com.tickets.exceptions.UserNotFoundException;
import com.tickets.model.Event;
import com.tickets.model.TicketType;
import com.tickets.model.User;
import com.tickets.repositories.EventRepository;
import com.tickets.repositories.UserRepository;
import com.tickets.request.CreateEventRequest;
import com.tickets.services.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

    private final UserRepository userRepository;
    private final EventRepository eventRepository;
    @Override
    public Event createEvent(UUID organizerId, CreateEventRequest event) {
        User organizer=userRepository.findById(organizerId)
                .orElseThrow(()-> new UserNotFoundException(String.format("User with id '%s' not found", organizerId)));

        List<TicketType> ticketTypesToCreate =event.getTicketTypes().stream().map(ticketType->{
            TicketType ticketTypeToCreate= new TicketType();
            ticketTypeToCreate.setName(ticketTypeToCreate.getName());
            ticketTypeToCreate.setPrice(ticketTypeToCreate.getPrice());
            ticketTypeToCreate.setDescription(ticketTypeToCreate.getDescription());
            ticketTypeToCreate.setTotalAvailable(ticketTypeToCreate.getTotalAvailable());
            return ticketTypeToCreate;
        }).toList();

        Event eventToCreate = new Event();
        eventToCreate.setName(event.getName());
        eventToCreate.setOrganizer(organizer);
        eventToCreate.setStart(event.getStart());
        eventToCreate.setEnd(event.getEnd());
        eventToCreate.setVenue(event.getVenue());
        eventToCreate.setSalesStart(event.getSalesStart());
        eventToCreate.setSalesEnd(event.getSalesEnd());
        eventToCreate.setStatus(event.getStatus());
        eventToCreate.setOrganizer(organizer);
        eventToCreate.setTicketTypes(ticketTypesToCreate);

        return eventRepository.save(eventToCreate);
    }

    @Override
    public Page<Event> listEventsForOrganizer(UUID organizerId, Pageable pageable) {
        return eventRepository.findByOrganizerId(organizerId,pageable);
    }


}
