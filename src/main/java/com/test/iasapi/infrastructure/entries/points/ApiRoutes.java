package com.test.iasapi.infrastructure.entries.points;

import com.test.iasapi.infrastructure.entries.points.event.EventHandler;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class ApiRoutes {

    @Bean
    public RouterFunction<ServerResponse> apiFunctional(EventHandler eventHandler) {
        return RouterFunctions.
                route(POST("/events").and(accept(MediaType.APPLICATION_JSON)), eventHandler::create)
                .andRoute(GET("/events").and(accept(MediaType.APPLICATION_JSON)), eventHandler::list)
                .andRoute(GET("/events/{id}").and(accept(MediaType.APPLICATION_JSON)), eventHandler::findById)
                .andRoute(PUT("/events/{id}").and(accept(MediaType.APPLICATION_JSON)), eventHandler::update)
                .andRoute(DELETE("/events/{id}").and(accept(MediaType.APPLICATION_JSON)), eventHandler::delete);


    }
}
