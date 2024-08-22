package com.eventregistration.system.controller;

import com.eventregistration.system.constants.AppUserErrorMessages;
import com.eventregistration.system.entity.Event;
import com.eventregistration.system.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;


@RestController
@RequestMapping("/api/events")

public class EventController {

    @Autowired
    private EventService eventService;

    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    @GetMapping
    public ResponseEntity<List<Event>> getAllEvents() {
        return ResponseEntity.ok(eventService.getAllEvents());
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping
    public ResponseEntity<String> createEvent(@RequestBody Event event) {
        eventService.createEvent(event);
        return ResponseEntity.status(AppUserErrorMessages.SUCCESSFULLY_ADDED.getStatus()).body(AppUserErrorMessages.SUCCESSFULLY_ADDED.getMessage());
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<String> updateEvent(@PathVariable Long id, @RequestBody Event eventDetails) {
        eventService.updateEvent(id, eventDetails);
        return ResponseEntity.status(AppUserErrorMessages.SUCCESSFULLY_UPDATED.getStatus()).body(AppUserErrorMessages.SUCCESSFULLY_UPDATED.getMessage());
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEvent(@PathVariable Long id) {
        eventService.deleteEvent(id);
        return ResponseEntity.status(AppUserErrorMessages.SUCCESSFULLY_DELETED.getStatus()).body(AppUserErrorMessages.SUCCESSFULLY_DELETED.getMessage());
    }
}

