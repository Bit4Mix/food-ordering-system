package com.ibabrou.food_ordering_system.dto;

import lombok.*;

@Data
public class DishDTO {
    private Long id;
    private String name;
    private String cuisine;
    private double price;
}
