package com.edu.todayperfume.perfume.entity;

public enum Weather {
    SUNNY("맑음"),
    RAINY("비"),
    SNOW("눈"),
    HOT("더움"),
    COLD("추움"),
    CLOUD("흐림");

    private final String name;

    Weather(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
