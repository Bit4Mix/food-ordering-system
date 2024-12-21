package com.ibabrou.food_ordering_system.service;

import com.ibabrou.food_ordering_system.entity.Drink;
import com.ibabrou.food_ordering_system.repository.DrinkRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DrinkServiceTest {

    @Mock
    private DrinkRepository drinkRepository;

    @InjectMocks
    private DrinkService drinkService;

    private Drink sampleDrink;

    @BeforeEach
    void setUp() {
        sampleDrink = new Drink();
        sampleDrink.setId(1L);
        sampleDrink.setName("Sample Drink");
        sampleDrink.setPrice(BigDecimal.valueOf(5.0));
    }

    @Test
    void givenValidId_whenGetDrinkById_thenReturnDrink() {

        Long id = 1L;
        when(drinkRepository.findById(id)).thenReturn(Optional.of(sampleDrink));

        Optional<Drink> result = drinkService.findDrinkById(id);

        assertTrue(result.isPresent());
        assertEquals(sampleDrink, result.get());
        verify(drinkRepository).findById(id);
    }

    @Test
    void givenInvalidId_whenGetDrinkById_thenReturnEmptyOptional() {

        Long id = 99L;
        when(drinkRepository.findById(id)).thenReturn(Optional.empty());

        Optional<Drink> result = drinkService.findDrinkById(id);

        assertTrue(result.isEmpty());
        verify(drinkRepository).findById(id);
    }
}