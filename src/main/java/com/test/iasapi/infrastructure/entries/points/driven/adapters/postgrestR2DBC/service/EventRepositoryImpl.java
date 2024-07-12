package com.test.iasapi.infrastructure.entries.points.driven.adapters.postgrestR2DBC.service;

import com.test.iasapi.application.ports.outbound.EventRepository;
import com.test.iasapi.domain.models.Event;
import com.test.iasapi.infrastructure.entries.points.driven.adapters.postgrestR2DBC.EventRepositoryReactive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class EventRepositoryImpl implements EventRepository {

    @Autowired
    private EventRepositoryReactive eventRepository;

    @Override
    public Flux<Event> findAll() {
        return eventRepository.findAll();
    }

    @Override
    public Mono<Event> findById(Long id) {
        return eventRepository.findById(id);
    }

    @Override
    public Mono<Event> save(Event event) {
        return eventRepository.save(event);
    }

    @Override
    public Mono<Event> update(Event event) {
        return eventRepository.save(event);
    }

    @Override
    public Mono deleteById(Long id) {
        return eventRepository.deleteById(id);
    }
}
