package com.test.iasapi.infrastructure.entries.points.event;

import com.test.iasapi.application.ports.inbound.*;
import com.test.iasapi.domain.models.Event;
import com.test.iasapi.infrastructure.entries.points.ApiRoutes;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;

@ExtendWith(SpringExtension.class)
@WebFluxTest({ApiRoutes.class, EventHandler.class})
class EventHandlerTest {

    @MockBean
    private CreateEventUseCase createEventUseCase;
    @MockBean
    private ListEventUseCase listEventUseCase;
    @MockBean
    private FindByIdUseCase findByIdUseCase;
    @MockBean
    private UpdateEventUseCase updateEventUseCase;
    @MockBean
    private DeleteByIdUseCase deleteByIdUseCase;

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void create() {

        Event event
                = Event.builder()
                .id(1L)
                .date(LocalDateTime.now())
                .location("40.7127281,-74.0060152")
                .build();

        given(createEventUseCase.execute(event)).willReturn(Mono.just(event));

        webTestClient
                .post()
                .uri("/events")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(event)
                .exchange()
                .expectStatus().isCreated()
                .expectBody(Event.class);
    }

    @Test
    void list() {
        when(listEventUseCase.execute(Optional.empty())).thenReturn(Flux.just(new Event(1l, "", LocalDateTime.now(), "")));
        webTestClient.get()
                .uri("/events")
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    void findById() {



    }

    @Test
    void update() {
        Event event
                = Event.builder()
                .id(1L)
                .date(LocalDateTime.now())
                .location("40.7127281,-74.0060152")
                .build();

        given(updateEventUseCase.execute(event)).willReturn(Mono.just(event));
        given(findByIdUseCase.execute(anyLong())).willReturn(Mono.just(event));

        webTestClient
                .put()
                .uri("/events/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(event)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Event.class);
    }

    @Test
    void delete() {
        Event event
                = Event.builder()
                .id(1L)
                .date(LocalDateTime.now())
                .location("40.7127281,-74.0060152")
                .build();
        given(findByIdUseCase.execute(anyLong())).willReturn(Mono.just(event));
        given(deleteByIdUseCase.execute(anyLong())).willReturn(Mono.empty());
        webTestClient
                .delete()
                .uri("/events/{id}", anyLong())
                .exchange()
                .expectStatus().isNoContent();

    }
}