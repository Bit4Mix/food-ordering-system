package com.ibabrou.food_ordering_system.config;

import com.ibabrou.food_ordering_system.controller.OrderController;
import com.ibabrou.food_ordering_system.io.ConsoleIO;
import com.ibabrou.food_ordering_system.io.DefaultConsoleIO;
import com.ibabrou.food_ordering_system.mapper.OrderMapper;
import com.ibabrou.food_ordering_system.mapper.OrderMapperImpl;
import com.ibabrou.food_ordering_system.runner.CliRunner;
import com.ibabrou.food_ordering_system.service.*;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.ibabrou.food_ordering_system.mapper")
public class AppConfig {

    @Bean
    DishService dishService() {
        return new DishService();
    }

    @Bean
    DessertService desertService() {
        return new DessertService();
    }

    @Bean
    DrinkService drinkService() {
        return new DrinkService();
    }

    @Bean
    OrderService orderService() {
        return new OrderService();
    }

    @Bean
    LunchOptionService lunchOptionService() {
        return new LunchOptionService();
    }

    @Bean
    DrinkOptionService drinkOptionService() {
        return new DrinkOptionService();
    }

    @Bean
    public ConsoleIO consoleIO() {
        return new DefaultConsoleIO();
    }

    @Bean
    @ConditionalOnMissingBean(CliRunner.class)
    public CliRunner cliRunner(OrderController orderController) {
        return new CliRunner(orderController);
    }

    @Bean
    public OrderMapper orderMapper() {
        return new OrderMapperImpl();
    }

    @Bean
    public OrderController orderController(ConsoleIO consoleIO,
                                           DishService dishService,
                                           DessertService dessertService,
                                           DrinkService drinkService,
                                           OrderService orderService,
                                           LunchOptionService lunchOptionService,
                                           DrinkOptionService drinkOptionService,
                                           OrderMapper orderMapper) {
        return new OrderController(consoleIO, dishService, dessertService, drinkService, orderService, lunchOptionService, drinkOptionService, orderMapper);
    }
}
