package com.ibabrou.food_ordering_system.service;

import com.ibabrou.food_ordering_system.entity.*;
import com.ibabrou.food_ordering_system.exception.DrinkNotFoundException;
import com.ibabrou.food_ordering_system.repository.DrinkOptionRepository;
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
class DrinkOptionServiceTest {

    @Mock
    private DrinkOptionRepository drinkOptionRepository;

    @Mock
    private DrinkService drinkService;

    @InjectMocks
    private DrinkOptionService drinkOptionService;

    private Drink sampleDrink;
    private DrinkOption sampleDrinkOption;

    @BeforeEach
    void setUp() {
        sampleDrink = new Drink();
        sampleDrink.setId(1L);
        sampleDrink.setName("Sample Drink");

        sampleDrinkOption = new DrinkOption();
        sampleDrinkOption.setId(1L);
        sampleDrinkOption.setDrink(sampleDrink);
        sampleDrinkOption.setWithIce(false);
        sampleDrinkOption.setWithLemon(true);
    }

    @Test
    void givenValidId_whenGetDrinkOptionById_thenReturnDrinkOption() {

        Long id = 1L;
        when(drinkOptionRepository.findById(id)).thenReturn(Optional.of(sampleDrinkOption));

        Optional<DrinkOption> result = drinkOptionService.getDrinkOptionById(id);

        assertTrue(result.isPresent());
        assertEquals(sampleDrinkOption, result.get());
        verify(drinkOptionRepository).findById(id);
    }

    @Test
    void givenInvalidId_whenGetDrinkOptionById_thenReturnEmptyOptional() {

        Long id = 99L;
        when(drinkOptionRepository.findById(id)).thenReturn(Optional.empty());

        Optional<DrinkOption> result = drinkOptionService.getDrinkOptionById(id);

        assertTrue(result.isEmpty());
        verify(drinkOptionRepository).findById(id);
    }

    @Test
    void givenValidDrinkId_whenCreate_thenSaveAndReturnDrinkOption() {
        long drinkId = 1L;
        boolean withIce = false;
        boolean withLemon = true;

        when(drinkService.findDrinkById(drinkId)).thenReturn(Optional.of(sampleDrink));
        when(drinkOptionRepository.save(any(DrinkOption.class))).thenReturn(sampleDrinkOption);

        DrinkOption result = drinkOptionService.createDrinkOption(drinkId, withIce, withLemon);

        assertNotNull(result);
        assertEquals(sampleDrinkOption, result);
        verify(drinkService).findDrinkById(drinkId);
        verify(drinkOptionRepository).save(any(DrinkOption.class));
    }

    @Test
    void givenInvalidDrinkId_whenCreate_thenThrowDrinkNotFoundException() {
        long drinkId = 99L;
        boolean withIce = false;
        boolean withLemon = true;

        when(drinkService.findDrinkById(drinkId)).thenReturn(Optional.empty());

        assertThrows(DrinkNotFoundException.class, () -> drinkOptionService.createDrinkOption(drinkId, withIce, withLemon));

        verify(drinkService).findDrinkById(drinkId);
        verify(drinkOptionRepository, never()).save(any(DrinkOption.class));
    }


}