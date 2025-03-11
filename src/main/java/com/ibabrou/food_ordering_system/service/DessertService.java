package com.ibabrou.food_ordering_system.service;

import com.ibabrou.food_ordering_system.entity.Dessert;
import com.ibabrou.food_ordering_system.repository.DessertRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DessertService {

    @Autowired
    private DessertRepository dessertRepository;

    public void showAvailableDesserts() {
        System.out.println("Available Desserts:");
        dessertRepository.findAll().forEach(dessert -> System.out.println(dessert.getId() + ": " + dessert.getName() + " - " + dessert.getPrice()));
    }

    public Optional<Dessert> findDessertById(long id) {
        return dessertRepository.findById(id);
    }
}
