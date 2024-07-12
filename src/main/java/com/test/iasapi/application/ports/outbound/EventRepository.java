package com.test.iasapi.application.ports.outbound;

import com.test.iasapi.domain.models.Event;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface EventRepository {
    Flux<Event> findAll();
    Mono<Event> findById(Long id);
    Mono<Event> save(Event event);
    Mono<Event> update(Event event);
    Mono deleteById(Long id);
}
