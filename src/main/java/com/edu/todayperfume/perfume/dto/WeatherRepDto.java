package com.edu.todayperfume.perfume.dto;

import java.util.List;

public record WeatherRepDto(
        List<Weather> weather,
        Main main
) {
    public record Weather(
            int id,
            String main,
            String description,
            String icon
    ) {}

    public record Main(
            double temp
    ) {}
}
