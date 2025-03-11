package com.ibabrou.food_ordering_system.controller;

import com.ibabrou.food_ordering_system.dto.*;
import com.ibabrou.food_ordering_system.exception.DessertNotFoundException;
import com.ibabrou.food_ordering_system.exception.DishNotFoundException;
import com.ibabrou.food_ordering_system.exception.DrinkNotFoundException;
import com.ibabrou.food_ordering_system.exception.MissingOrderOptionsException;
import com.ibabrou.food_ordering_system.io.ConsoleIO;
import com.ibabrou.food_ordering_system.mapper.*;
import com.ibabrou.food_ordering_system.service.*;
import org.springframework.stereotype.Controller;

import java.util.Optional;


@Controller
public class OrderController {

    private final ConsoleIO console;
    private final DishService dishService;
    private final DessertService dessertService;
    private final DrinkService drinkService;
    private final OrderService orderService;
    private final LunchOptionService lunchOptionService;
    private final DrinkOptionService drinkOptionService;
    private final OrderMapper orderMapper;

    public OrderController(ConsoleIO console,
                           DishService dishService,
                           DessertService dessertService,
                           DrinkService drinkService,
                           OrderService orderService,
                           LunchOptionService lunchOptionService,
                           DrinkOptionService drinkOptionService,
                           OrderMapper orderMapper) {
        this.console = console;
        this.dishService = dishService;
        this.dessertService = dessertService;
        this.drinkService = drinkService;
        this.orderService = orderService;
        this.lunchOptionService = lunchOptionService;
        this.drinkOptionService = drinkOptionService;
        this.orderMapper = orderMapper;
    }


    public void processOrder() {
        console.print("Welcome to the ordering system!");

        while (true) {
            console.print("Enter the command (1 - order, 2 - view all orders, 3 - exit):");
            int mainCommand = console.readInt();
            console.readLine();

            if (mainCommand == 1) {
                handleOrderCreation();
            } else if (mainCommand == 2) {
                handleViewAllOrders();
            } else if (mainCommand == 3) {
                console.print("Exit the application.");
                break;
            } else {
                console.print("Unknown command. Try again.");
            }
        }
    }

    private void handleOrderCreation() {
        Optional<Long> lunchOptionId = handleLunchCreation();
        Optional<Long> drinkOptionId = handleDrinkCreation();

        try {
            OrderDTO orderDTO = orderMapper.toOrderDTO(orderService.createOrder(lunchOptionId, drinkOptionId));
            console.print("====YOUR ORDER====");
            orderService.showInfoByOrderId(orderDTO.getId());
        } catch (MissingOrderOptionsException e) {
            console.print(e.getMessage());
        }
    }

    private Optional<Long> handleLunchCreation() {
        console.print("Would you like to order lunch? (1 - yes, 2 - no):");
        int lunchOptionChoice = console.readInt();
        console.readLine();

        if (lunchOptionChoice != 1) {
            return Optional.empty();
        }

        dishService.showAvailableDishes();
        console.print("Enter dish ID:");
        Long dishId = console.readLong();
        console.readLine();

        dessertService.showAvailableDesserts();
        console.print("Enter dessert ID:");
        Long dessertId = console.readLong();
        console.readLine();

        try {
            return Optional.of(lunchOptionService.createLunchOption(dishId, dessertId).getId());
        } catch (DishNotFoundException | DessertNotFoundException e) {
            console.print("Error: " + e.getMessage());
            return Optional.empty();
        } catch (Exception e) {
            console.print("Unknown error: " + e.getMessage());
            return Optional.empty();
        }
    }

    private Optional<Long> handleDrinkCreation() {
        console.print("Would you like to add a drink to your order? (1 - yes, 2 - no):");
        int drinkChoice = console.readInt();
        console.readLine();

        if (drinkChoice != 1) {
            return Optional.empty();
        }

        drinkService.showAvailableDrinks();
        console.print("Enter drink ID:");
        Long drinkId = console.readLong();
        console.readLine();

        console.print("Would you like to add ice? (y/n)");
        boolean withIce = "y".equalsIgnoreCase(console.readLine());

        console.print("Would you like to add lemon? (y/n)");
        boolean withLemon = "y".equalsIgnoreCase(console.readLine());

        try {
            return Optional.of(drinkOptionService.createDrinkOption(drinkId, withIce, withLemon).getId());
        } catch (DrinkNotFoundException e) {
            console.print("Error: " + e.getMessage());
            return Optional.empty();
        }
    }

    private void handleViewAllOrders() {
        console.print("All orders:");
        orderService.showAllOrders();
    }
}
