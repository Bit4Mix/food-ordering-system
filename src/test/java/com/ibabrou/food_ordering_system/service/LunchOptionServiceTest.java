package com.ibabrou.food_ordering_system.service;

import com.ibabrou.food_ordering_system.entity.Dessert;
import com.ibabrou.food_ordering_system.entity.Dish;
import com.ibabrou.food_ordering_system.entity.LunchOption;
import com.ibabrou.food_ordering_system.exception.DessertNotFoundException;
import com.ibabrou.food_ordering_system.exception.DishNotFoundException;
import com.ibabrou.food_ordering_system.repository.LunchOptionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LunchOptionServiceTest {

    @Mock
    private LunchOptionRepository lunchOptionRepository;

    @Mock
    private DishService dishService;

    @Mock
    private DessertService dessertService;

    @InjectMocks
    private LunchOptionService lunchOptionService;

    private Dish sampleDish;
    private Dessert sampleDessert;
    private LunchOption sampleLunchOption;


    @BeforeEach
    void setUp() {
        sampleDish = new Dish();
        sampleDish.setId(1L);
        sampleDish.setName("Sample Dish");

        sampleDessert = new Dessert();
        sampleDessert.setId(2L);
        sampleDessert.setName("Sample Dessert");

        sampleLunchOption = new LunchOption();
        sampleLunchOption.setId(1L);
        sampleLunchOption.setDish(sampleDish);
        sampleLunchOption.setDessert(sampleDessert);
    }

    @Test
    void givenValidId_whenGetLunchOptionById_thenReturnLunchOption() {

        Long id = 1L;
        when(lunchOptionRepository.findById(id)).thenReturn(Optional.of(sampleLunchOption));

        Optional<LunchOption> result = lunchOptionService.getLunchOptionById(id);

        assertTrue(result.isPresent());
        assertEquals(sampleLunchOption, result.get());
        verify(lunchOptionRepository).findById(id);
    }

    @Test
    void givenInvalidId_whenGetLunchOptionById_thenReturnEmptyOptional() {

        Long id = 99L;
        when(lunchOptionRepository.findById(id)).thenReturn(Optional.empty());

        Optional<LunchOption> result = lunchOptionService.getLunchOptionById(id);

        assertTrue(result.isEmpty());
        verify(lunchOptionRepository).findById(id);
    }

    @Test
    void givenValidDishAndDessertIds_whenCreate_LunchOption_thenSaveAndReturnLunchOption() {
        long dishId = 1L;
        long dessertId = 2L;

        when(dishService.findDishById(dishId)).thenReturn(Optional.of(sampleDish));
        when(dessertService.findDessertById(dessertId)).thenReturn(Optional.of(sampleDessert));
        when(lunchOptionRepository.save(any(LunchOption.class))).thenReturn(sampleLunchOption);

        LunchOption result = lunchOptionService.createLunchOption(dishId, dessertId);

        assertNotNull(result);
        assertEquals(sampleLunchOption, result);
        verify(dishService).findDishById(dishId);
        verify(dessertService).findDessertById(dessertId);
        verify(lunchOptionRepository).save(any(LunchOption.class));
    }

    @Test
    void givenInvalidDishId_whenCreate_LunchOption_thenThrowDishNotFoundException() {
        long dishId = 99L;
        long dessertId = 2L;

        when(dishService.findDishById(dishId)).thenReturn(Optional.empty());

        assertThrows(DishNotFoundException.class, () -> lunchOptionService.createLunchOption(dishId, dessertId));

        verify(dishService).findDishById(dishId);
        verify(dessertService, never()).findDessertById(anyLong());
        verify(lunchOptionRepository, never()).save(any(LunchOption.class));
    }

    @Test
    void givenInvalidDessertId_whenCreate_LunchOption_thenThrowDessertNotFoundException() {
        long dishId = 1L;
        long dessertId = 99L;

        when(dishService.findDishById(dishId)).thenReturn(Optional.of(sampleDish));
        when(dessertService.findDessertById(dessertId)).thenReturn(Optional.empty());

        assertThrows(DessertNotFoundException.class, () -> lunchOptionService.createLunchOption(dishId, dessertId));

        verify(dishService).findDishById(dishId);
        verify(dessertService).findDessertById(dessertId);
        verify(lunchOptionRepository, never()).save(any(LunchOption.class));
    }

}