package com.test.iasapi.infrastructure.entries.points.driven.adapters.postgrestR2DBC;

import com.test.iasapi.domain.models.Event;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepositoryReactive extends ReactiveCrudRepository<Event, Long> {
}
