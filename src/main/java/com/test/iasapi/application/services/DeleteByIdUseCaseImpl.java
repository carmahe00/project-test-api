package com.test.iasapi.application.services;

import com.test.iasapi.application.ports.inbound.DeleteByIdUseCase;
import com.test.iasapi.application.ports.outbound.EventRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class DeleteByIdUseCaseImpl implements DeleteByIdUseCase {

    private final EventRepository eventRepository;
    @Override
    public Mono<String> execute(Long id) {
        return eventRepository.deleteById(id);
    }
}
