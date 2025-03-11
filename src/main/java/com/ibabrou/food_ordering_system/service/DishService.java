package com.ibabrou.food_ordering_system.service;

import com.ibabrou.food_ordering_system.entity.Dish;
import com.ibabrou.food_ordering_system.repository.DishRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DishService {

    @Autowired
    private DishRepository dishRepository;


    public void showAvailableDishes() {
        System.out.println("Available Dishes:");
        dishRepository.findAll().forEach(dish -> System.out.println(dish.getId() + ": " + dish.getName() + " - " + dish.getPrice() + " - " + dish.getCuisine()));
    }

    public Optional<Dish> findDishById(long id) {
        return dishRepository.findById(id);
    }
}
