package com.ibabrou.food_ordering_system.service;

import com.ibabrou.food_ordering_system.entity.Dessert;

import com.ibabrou.food_ordering_system.repository.DessertRepository;
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
class DessertServiceTest {
    @Mock
    private DessertRepository dessertRepository;

    @InjectMocks
    private DessertService dessertService;

    private Dessert savedDessert;

    @BeforeEach
    void setUp() {
        savedDessert = new Dessert();
        savedDessert.setId(1L);
        savedDessert.setName("Sample Dessert");
        savedDessert.setPrice(BigDecimal.valueOf(7.0));
    }

    @Test
    void givenValidId_whenGetDessertById_thenReturnDessert() {

        Long id = 1L;
        when(dessertRepository.findById(id)).thenReturn(Optional.of(savedDessert));

        Optional<Dessert> result = dessertService.findDessertById(id);

        assertTrue(result.isPresent());
        assertEquals(savedDessert, result.get());
        verify(dessertRepository).findById(id);
    }

    @Test
    void givenInvalidId_whenGetDessertById_thenReturnEmptyOptional() {

        Long id = 99L;
        when(dessertRepository.findById(id)).thenReturn(Optional.empty());

        Optional<Dessert> result = dessertService.findDessertById(id);

        assertTrue(result.isEmpty());
        verify(dessertRepository).findById(id);
    }
}