package com.ibabrou.food_ordering_system.dto;

import lombok.*;

@Data
public class DrinkOptionDTO {

    private long id;
    private DrinkDTO drink;
    private Boolean withIce;
    private Boolean withLemon;

}
