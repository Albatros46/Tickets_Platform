package com.tickets.mappers;

import com.tickets.dtos.CreateEventRequestDto;
import com.tickets.dtos.CreateEventResponseDto;
import com.tickets.dtos.CreateTicketTypeRequestDto;
import com.tickets.model.Event;
import com.tickets.request.CreateEventRequest;
import com.tickets.request.CreateTicketTypeRequest;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface EventMapper {

    CreateTicketTypeRequest fromDto(CreateTicketTypeRequestDto dto);
    CreateEventRequest fromDto(CreateEventRequestDto dto);

    CreateEventResponseDto toDto(Event event);
}
