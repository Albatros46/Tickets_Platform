package com.tickets.controller;

import com.tickets.dtos.CreateEventRequestDto;
import com.tickets.dtos.CreateEventResponseDto;
import com.tickets.mappers.EventMapper;
import com.tickets.model.Event;
import com.tickets.request.CreateEventRequest;
import com.tickets.services.EventService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/events")
@RequiredArgsConstructor
public class EventController {

    private final EventMapper eventMapper;
    private final EventService eventService;

    public ResponseEntity<CreateEventResponseDto> createEvent(@AuthenticationPrincipal Jwt jwt,
                                                              @Valid @RequestBody CreateEventRequestDto createEventRequestDto){
        CreateEventRequest createEventRequest =eventMapper.fromDto(createEventRequestDto);
        UUID userId=UUID.fromString(jwt.getSubject());

        Event createdEvent=eventService.createEvent(userId,createEventRequest);
        CreateEventResponseDto createEventResponseDto=eventMapper.toDto(createdEvent);
        return new ResponseEntity<>(createEventResponseDto, HttpStatus.CREATED);
    }

}
