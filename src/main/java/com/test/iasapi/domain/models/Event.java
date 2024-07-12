package com.test.iasapi.domain.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Data
@Table("events")
@AllArgsConstructor
@Builder
public class Event {
    @Id
    private Long id;
    private String name;
    private LocalDateTime date;
    private String location;
}
