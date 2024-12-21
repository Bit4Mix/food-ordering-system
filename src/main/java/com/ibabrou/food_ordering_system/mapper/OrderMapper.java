package com.ibabrou.food_ordering_system.mapper;

import com.ibabrou.food_ordering_system.dto.*;
import com.ibabrou.food_ordering_system.entity.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "totalPrice", target = "totalPrice")
    @Mapping(source = "lunchOption.id", target = "lunchOptionDTO.id")
    @Mapping(source = "drinkOption.id", target = "drinkOptionDTO.id")
    OrderDTO toOrderDTO(Order order);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "totalPrice", target = "totalPrice")
    @Mapping(source = "lunchOptionDTO.id", target = "lunchOption.id")
    @Mapping(source = "drinkOptionDTO.id", target = "drinkOption.id")
    Order toOrder(OrderDTO orderDTO);

}
