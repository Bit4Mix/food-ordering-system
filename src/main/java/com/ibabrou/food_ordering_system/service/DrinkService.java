package com.ibabrou.food_ordering_system.service;

import com.ibabrou.food_ordering_system.entity.Drink;
import com.ibabrou.food_ordering_system.repository.DrinkRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DrinkService {

    @Autowired
    private DrinkRepository drinkRepository;

    public void showAvailableDrinks() {
        System.out.println("Available Drinks:");
        drinkRepository.findAll().forEach(drink -> System.out.println(drink.getId() + ": " + drink.getName() + " - " + drink.getPrice()));
    }

    public Optional<Drink> findDrinkById(long id) {
        return drinkRepository.findById(id);
    }

}
