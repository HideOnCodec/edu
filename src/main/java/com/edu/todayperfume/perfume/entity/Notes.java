package com.edu.todayperfume.perfume.entity;

import com.edu.todayperfume.global.BaseTimeEntity;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder(toBuilder = true)
public class Notes extends BaseTimeEntity {
    private int id;
    private String name;
    private Type type;

    public static Notes of(int id, String name, Type type) {
        return Notes.builder()
                .id(id)
                .name(name)
                .type(type)
                .build();
    }
}
