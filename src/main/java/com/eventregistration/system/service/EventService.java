package com.eventregistration.system.service;

import com.eventregistration.system.constants.AppUserErrorMessages;
import com.eventregistration.system.entity.Event;
import com.eventregistration.system.exception.ResourceNotFoundException;
import com.eventregistration.system.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    public Event createEvent(Event event) {
        return eventRepository.save(event);
    }

    public Event updateEvent(Long id, Event eventDetails) {
        Event event = eventRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(AppUserErrorMessages.EVENT_NOT_FOUND));
        event.setName(eventDetails.getName());
        event.setDate(eventDetails.getDate());
        event.setLocation(eventDetails.getLocation());
        return eventRepository.save(event);
    }

    public void deleteEvent(Long id) {
        Event event = eventRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(AppUserErrorMessages.EVENT_NOT_FOUND));
        eventRepository.delete(event);
    }
}

