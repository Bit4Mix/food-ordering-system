package com.ibabrou.food_ordering_system.dto;

import lombok.*;

@Data
public class OrderDTO {

    private Long id;
    private Double totalPrice;
    private LunchOptionDTO lunchOptionDTO;
    private DrinkOptionDTO drinkOptionDTO;

}
