package com.test.iasapi.application.services;

import com.test.iasapi.application.ports.inbound.UpdateEventUseCase;
import com.test.iasapi.application.ports.outbound.EventRepository;
import com.test.iasapi.domain.models.Event;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class UpdateEventUseCaseImpl implements UpdateEventUseCase {
    private final EventRepository eventRepository;
    @Override
    public Mono<Event> execute(Event event) {
        return eventRepository.update(event);
    }
}
