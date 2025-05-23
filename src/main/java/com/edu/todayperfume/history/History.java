package com.edu.todayperfume.history;

import lombok.Builder;
import lombok.Getter;

import java.sql.*;
import java.time.LocalDateTime;

@Getter
@Builder
public class History{
    private int id;
    private String entityName;
    private String entityId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String creator;
    private String updater;

    public static History create(TableType type, String id, String creator){
        return History.builder()
                .entityId(id)
                .creator(creator)
                .entityName(type.name())
                .updatedAt(LocalDateTime.now())
                .createdAt(LocalDateTime.now())
                .updater(creator)
                .build();
    }

    public static History update(TableType type, String id, String updater){
        return History.builder()
                .entityId(id)
                .creator(null)
                .entityName(type.name())
                .updatedAt(LocalDateTime.now())
                .createdAt(null)
                .updater(updater)
                .build();
    }
}
