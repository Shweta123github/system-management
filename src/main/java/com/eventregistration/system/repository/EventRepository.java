package com.eventregistration.system.repository;

import com.eventregistration.system.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long> {


}