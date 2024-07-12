package com.test.iasapi.infrastructure.entries.points.event;

import com.test.iasapi.application.ports.inbound.*;
import com.test.iasapi.domain.models.Event;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import javax.swing.text.html.Option;
import java.util.Optional;

@Tag(
        name = "CRUD REST APIs for IAS",
        description = "CRUD REST APIs in IAS Events"
)
@Component
@AllArgsConstructor
public class EventHandler {

    private final CreateEventUseCase createEventUseCase;

    private final ListEventUseCase listEventUseCase;

    private final FindByIdUseCase findByIdUseCase;

    private final UpdateEventUseCase updateEventUseCase;

    private final DeleteByIdUseCase deleteByIdUseCase;

    @Operation(
            summary = "Create Event REST API",
            description = "REST API to create new event"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "HTTP Status CREATED"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error"
            )
    }
    )
    public Mono<ServerResponse> create(ServerRequest serverRequest) {
        return serverRequest.bodyToMono(Event.class)
                .flatMap(createEventUseCase::execute)
                .flatMap(savedEvent -> ServerResponse
                        .status(HttpStatus.CREATED)
                        .bodyValue(savedEvent)
                )
                .onErrorResume(exception -> ServerResponse.unprocessableEntity().bodyValue("Error create Event"));
    }

    @Operation(
            summary = "List Event REST API",
            description = "REST API to create new event"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "20",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "204",
                    description = "HTTP Not content"
            )
    }
    )
    public Mono<ServerResponse> list(ServerRequest serverRequest) {

        return listEventUseCase.execute(Optional.empty())
                .collectList()
                .flatMap(listEvents -> ServerResponse.ok()
                        .bodyValue(listEvents))
                .switchIfEmpty(ServerResponse
                        .status(HttpStatus.NO_CONTENT)
                        .bodyValue("Event not found"));

    }

    @Operation(
            summary = "Find Event REST API",
            description = "REST API to create new event"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "HTTP Not content"
            )
    }
    )
    public Mono<ServerResponse> findById(ServerRequest serverRequest) {
        return findByIdUseCase.execute(Long.valueOf(serverRequest.pathVariable("id")))
                .flatMap(event -> ServerResponse.
                        ok()
                        .bodyValue(event)
                )
                .switchIfEmpty(ServerResponse
                        .status(HttpStatus.NO_CONTENT)
                        .bodyValue("Event not found")
                );

    }


    @Operation(
            summary = "Update Event REST API",
            description = "REST API to create new event"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status Updated"
            )
    }
    )
    public Mono<ServerResponse> update(ServerRequest serverRequest) {
        return findByIdUseCase
                .execute(Long.valueOf(serverRequest.pathVariable("id")))
                .flatMap(eventFound -> serverRequest
                        .bodyToMono(Event.class)
                        .flatMap(eventRequest -> {
                            eventRequest.setId(eventFound.getId());
                            return updateEventUseCase.execute(eventRequest);
                        })
                        .flatMap(userUpdated -> ServerResponse
                                .status(HttpStatus.OK)
                                .bodyValue(userUpdated)
                        )
                )
                .switchIfEmpty(ServerResponse
                        .status(HttpStatus.NOT_FOUND)
                        .bodyValue("Event not found")
                );
    }

    @Operation(
            summary = "delete Event REST API",
            description = "REST API to create new event"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "204",
                    description = "HTTP Status Not content"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "HTTP Status Not found"
            )
    }
    )
    public Mono<ServerResponse> delete(ServerRequest serverRequest) {
        return findByIdUseCase
                .execute(Long.valueOf(serverRequest.pathVariable("id")))
                .flatMap(eventFound ->
                        deleteByIdUseCase.execute(eventFound.getId())
                                .then(ServerResponse.status(HttpStatus.NO_CONTENT).build())
                )
                .switchIfEmpty(ServerResponse
                        .status(HttpStatus.NOT_FOUND)
                        .bodyValue("Event not found")
                );
    }
}
