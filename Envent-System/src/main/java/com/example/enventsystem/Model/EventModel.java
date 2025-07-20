package com.example.enventsystem.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class EventModel {
    private long id;
    private String description;
    private int capacity;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm")
    private LocalDateTime startDate;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm")
    private LocalDateTime endDate;
}
