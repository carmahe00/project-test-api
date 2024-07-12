package com.test.iasapi.application.ports.inbound;

import com.test.iasapi.commons.UseCase;
import com.test.iasapi.domain.models.Event;
import reactor.core.publisher.Mono;

public interface CreateEventUseCase extends UseCase<Event, Mono<Event>> {

}
