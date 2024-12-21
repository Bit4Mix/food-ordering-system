package com.ibabrou.food_ordering_system.service;

import com.ibabrou.food_ordering_system.entity.*;

import com.ibabrou.food_ordering_system.exception.MissingOrderOptionsException;
import com.ibabrou.food_ordering_system.repository.OrderRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private LunchOptionService lunchOptionService;
    @Autowired
    private DrinkOptionService drinkOptionService;


    public void showAllOrders() {
        orderRepository.findAll().forEach(order -> this.showInfoByOrderId(order.getId()));
    }

    @Transactional
    public Order createOrder(Optional<Long> lunchOptionId, Optional<Long> drinkOptionId) {
        if (lunchOptionId.isEmpty() && drinkOptionId.isEmpty()) {
            throw new MissingOrderOptionsException("Error when creating an order. At least one of LunchOption or DrinkOption must be provided.");
        }
        Order order = new Order();
        lunchOptionId.flatMap(id -> lunchOptionService.getLunchOptionById(id)).ifPresent(order::setLunchOption);
        drinkOptionId.flatMap(id -> drinkOptionService.getDrinkOptionById(id)).ifPresent(order::setDrinkOption);
        order.setTotalPrice(order.calculateTotalPrice());
        return orderRepository.save(order);
    }


    public void showInfoByOrderId(long orderId) {
        orderRepository.findById(orderId).ifPresent(order -> {
            System.out.println("Order ID: " + orderId);
            if (order.getLunchOption()!=null) {
                System.out.println("Dish: " + order.getLunchOption().getDish().getName());
                System.out.println("Dessert: " + order.getLunchOption().getDessert().getName());
            }
            if (order.getDrinkOption()!=null) {
                System.out.println("Drink: " + order.getDrinkOption().getDrink().getName() + " | " + (order.getDrinkOption().isWithIce() ? "With ice" : "No ice") + " | " + (order.getDrinkOption().isWithLemon() ? "With lemon" : "No lemon"));
            }
            System.out.println("Total Price: " + order.getTotalPrice());
        });
    }

}
