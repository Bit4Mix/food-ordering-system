package com.ibabrou.food_ordering_system.dto;

import lombok.*;

@Data
@NoArgsConstructor
public class LunchOptionDTO {

    private long id;
    private DishDTO dish;
    private DessertDTO dessert;
}
