package com.test.iasapi.application.ports.inbound;

import com.test.iasapi.commons.UseCase;
import com.test.iasapi.domain.models.Event;
import reactor.core.publisher.Flux;

import java.util.Optional;

public interface ListEventUseCase extends UseCase<Optional, Flux<Event>> {
}
