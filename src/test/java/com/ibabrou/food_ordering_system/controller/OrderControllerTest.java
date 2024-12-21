package com.ibabrou.food_ordering_system.controller;

import com.ibabrou.food_ordering_system.io.ConsoleIO;
import com.ibabrou.food_ordering_system.service.LunchOptionService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.ibabrou.food_ordering_system.service.*;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OrderControllerTest {

    @Mock
    private ConsoleIO console;

    @Mock
    private OrderService orderService;

    @Mock
    private LunchOptionService lunchOptionService;

    @Mock
    private DrinkOptionService drinkOptionService;

    @InjectMocks
    private OrderController orderController;


    @Test
    void testProcessOrder_InvalidCommand() {
        when(console.readInt()).thenReturn(4) // Choose invalid command
                .thenReturn(3);

        orderController.processOrder();

        verify(console).print("Unknown command. Try again.");
        verify(console).print("Exit the application.");
    }

    @Test
    void testProcessOrder_ViewAllOrders() {
        when(console.readInt()).thenReturn(2) // Choose "view all orders" command
                .thenReturn(3); // Choose exit command
        doNothing().when(orderService).showAllOrders();

        orderController.processOrder();

        verify(console, times(1)).print(contains("All orders:"));
        verify(orderService, times(1)).showAllOrders();
        verify(console).print("Exit the application.");
    }

    @Test
    void testProcessOrder_ExitApplication() {
        when(console.readInt()).thenReturn(3); // Choose exit command

        orderController.processOrder();

        verify(console, times(1)).print(contains("Exit the application."));
        verifyNoMoreInteractions(orderService, lunchOptionService, drinkOptionService);
    }
}