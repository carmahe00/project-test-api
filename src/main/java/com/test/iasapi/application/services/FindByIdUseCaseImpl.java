package com.test.iasapi.application.services;

import com.test.iasapi.application.ports.inbound.FindByIdUseCase;
import com.test.iasapi.application.ports.outbound.EventRepository;
import com.test.iasapi.domain.models.Event;
import com.test.iasapi.domain.models.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class FindByIdUseCaseImpl implements FindByIdUseCase {
    private final EventRepository eventRepository;
    @Override
    public Mono<Event> execute(Long id) {
        return eventRepository.findById(id);
    }
}
