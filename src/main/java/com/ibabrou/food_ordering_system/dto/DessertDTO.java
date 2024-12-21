package com.ibabrou.food_ordering_system.dto;

import lombok.*;

@Data
public class DessertDTO {
    private Long id;
    private String name;
    private String description;
    private double price;
}