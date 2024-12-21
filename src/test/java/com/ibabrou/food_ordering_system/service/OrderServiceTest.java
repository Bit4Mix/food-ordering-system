package com.ibabrou.food_ordering_system.service;

import com.ibabrou.food_ordering_system.entity.*;
import com.ibabrou.food_ordering_system.exception.MissingOrderOptionsException;
import com.ibabrou.food_ordering_system.repository.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private LunchOptionService lunchOptionService;

    @Mock
    private DrinkOptionService drinkOptionService;

    @InjectMocks
    private OrderService orderService;

    private LunchOption lunchOption;
    private DrinkOption drinkOption;


    @BeforeEach
    void setUp() {
        // Mock LunchOption
        Dish dish = new Dish();
        dish.setId(1L);
        dish.setPrice(BigDecimal.valueOf(10.0));

        Dessert dessert = new Dessert();
        dessert.setId(2L);
        dessert.setPrice(BigDecimal.valueOf(5.0));

        lunchOption = new LunchOption();
        lunchOption.setId(1L);
        lunchOption.setDish(dish);
        lunchOption.setDessert(dessert);

        // Mock DrinkOption
        Drink drink = new Drink();
        drink.setId(1L);
        drink.setPrice(BigDecimal.valueOf(3.0));

        drinkOption = new DrinkOption();
        drinkOption.setId(1L);
        drinkOption.setDrink(drink);
        drinkOption.setWithIce(true);
        drinkOption.setWithLemon(false);
    }


    @Test
    void testCreateOrder_Success() {
        when(lunchOptionService.getLunchOptionById(1L)).thenReturn(Optional.of(lunchOption));
        when(drinkOptionService.getDrinkOptionById(1L)).thenReturn(Optional.of(drinkOption));

        Order savedOrder = new Order();
        savedOrder.setId(1L);
        savedOrder.setLunchOption(lunchOption);
        savedOrder.setDrinkOption(drinkOption);
        savedOrder.setTotalPrice(BigDecimal.valueOf(18.0));

        when(orderRepository.save(any(Order.class))).thenReturn(savedOrder);

        Order createdOrder = orderService.createOrder(Optional.of(1L), Optional.of(1L));

        assertNotNull(createdOrder);
        assertEquals(BigDecimal.valueOf(18.0), createdOrder.getTotalPrice());
        verify(orderRepository, times(1)).save(any(Order.class));
    }

    @Test
    void givenEmptyLunchOption_whenCreateOrder_thenSuccess() {

        when(lunchOptionService.getLunchOptionById(1L)).thenReturn(Optional.empty());
        when(drinkOptionService.getDrinkOptionById(1L)).thenReturn(Optional.of(drinkOption));

        Order savedOrder = new Order();
        savedOrder.setDrinkOption(drinkOption);
        savedOrder.setTotalPrice(BigDecimal.valueOf(3.0));

        when(orderRepository.save(any(Order.class))).thenReturn(savedOrder);
        Order createdOrder = orderService.createOrder(Optional.of(1L), Optional.of(1L));

        verify(orderRepository, times(1)).save(any(Order.class));

        assertNotNull(createdOrder);
        assertNull(createdOrder.getLunchOption());
        assertNotNull(createdOrder.getDrinkOption());
        assertEquals(BigDecimal.valueOf(3.0), createdOrder.getTotalPrice());
    }

    @Test
    void givenEmptyDrinkOption_whenCreateOrder_thenSuccess() {

        when(lunchOptionService.getLunchOptionById(1L)).thenReturn(Optional.of(lunchOption));
        when(drinkOptionService.getDrinkOptionById(1L)).thenReturn(Optional.empty());

        Order savedOrder = new Order();
        savedOrder.setLunchOption(lunchOption);
        savedOrder.setTotalPrice(BigDecimal.valueOf(15.0));

        when(orderRepository.save(any(Order.class))).thenReturn(savedOrder);

        Order createdOrder = orderService.createOrder(Optional.of(1L), Optional.of(1L));


        verify(orderRepository, times(1)).save(any(Order.class));

        assertNotNull(createdOrder);
        assertNotNull(createdOrder.getLunchOption());
        assertNull(createdOrder.getDrinkOption());
        assertEquals(BigDecimal.valueOf(15.0), createdOrder.getTotalPrice());
    }

    @Test
    void givenBothOptionsAbsent_whenCreateOrder_thenThrowMissingOrderOptionsException() {
        Optional<Long> emptyLunchOptionId = Optional.empty();
        Optional<Long> emptyDrinkOptionId = Optional.empty();

        assertThrows(MissingOrderOptionsException.class, () ->
                orderService.createOrder(emptyLunchOptionId, emptyDrinkOptionId)
        );
    }

}