package com.test.iasapi.application.ports.inbound;

import com.test.iasapi.commons.UseCase;
import reactor.core.publisher.Mono;

public interface DeleteByIdUseCase extends UseCase<Long, Mono<String>> {
}
