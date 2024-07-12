package com.test.iasapi.application.services;

import com.test.iasapi.application.ports.inbound.ListEventUseCase;
import com.test.iasapi.application.ports.outbound.EventRepository;
import com.test.iasapi.domain.models.Event;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.Optional;

@Service
@AllArgsConstructor
public class ListEventUseCaseImpl implements ListEventUseCase {
    private final EventRepository eventRepository;
    @Override
    public Flux<Event> execute(Optional optional) {
        return eventRepository.findAll();
    }
}
