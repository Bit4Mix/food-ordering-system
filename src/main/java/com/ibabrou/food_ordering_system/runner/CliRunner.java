package com.ibabrou.food_ordering_system.runner;

import com.ibabrou.food_ordering_system.controller.OrderController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

import org.springframework.stereotype.Component;

@Component
public class CliRunner implements CommandLineRunner {

    private final OrderController orderController;

    @Autowired
    public CliRunner(OrderController orderController) {
        this.orderController = orderController;
    }

    @Override
    public void run(String... args) {
        orderController.processOrder();
    }
}