package com.ibabrou.food_ordering_system.service;

import com.ibabrou.food_ordering_system.entity.Dish;
import com.ibabrou.food_ordering_system.repository.DishRepository;
import com.ibabrou.food_ordering_system.entity.Cuisine;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class DishServiceTest {
    @Mock
    private DishRepository dishRepository;

    @InjectMocks
    private DishService dishService;

    private Dish sampleDish;

    @BeforeEach
    void setUp() {
        sampleDish = new Dish();
        sampleDish.setId(1L);
        sampleDish.setName("Sample Dish");
        sampleDish.setCuisine(Cuisine.POLISH);
        sampleDish.setPrice(BigDecimal.valueOf(10.0));
    }

    @Test
    void givenValidId_whenGetDishById_thenReturnDish() {

        Long id = 1L;
        when(dishRepository.findById(id)).thenReturn(Optional.of(sampleDish));

        Optional<Dish> result = dishService.findDishById(id);

        assertTrue(result.isPresent());
        assertEquals(sampleDish, result.get());
        verify(dishRepository).findById(id);
    }

    @Test
    void givenInvalidId_whenGetDishById_thenReturnEmptyOptional() {

        Long id = 99L;
        when(dishRepository.findById(id)).thenReturn(Optional.empty());

        Optional<Dish> result = dishService.findDishById(id);

        assertTrue(result.isEmpty());
        verify(dishRepository).findById(id);
    }
}