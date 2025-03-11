package com.ibabrou.food_ordering_system.service;

import com.ibabrou.food_ordering_system.entity.Dessert;
import com.ibabrou.food_ordering_system.entity.Dish;
import com.ibabrou.food_ordering_system.entity.LunchOption;
import com.ibabrou.food_ordering_system.exception.DessertNotFoundException;
import com.ibabrou.food_ordering_system.exception.DishNotFoundException;
import com.ibabrou.food_ordering_system.repository.LunchOptionRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LunchOptionService {

    @Autowired
    private LunchOptionRepository lunchOptionRepository;
    @Autowired
    private DishService dishService;
    @Autowired
    private DessertService dessertService;

    public Optional<LunchOption> getLunchOptionById(long id) {
        return lunchOptionRepository.findById(id);
    }

    @Transactional
    public LunchOption createLunchOption(Long dishId, Long dessertId) throws DishNotFoundException, DessertNotFoundException {
        // checkingTheAvailabilityOfDish
        Dish dish = dishService.findDishById(dishId)
                .orElseThrow(() -> new DishNotFoundException("dishWithID " + dishId + " not found"));

        // checkingTheAvailabilityOfDessert
        Dessert dessert = dessertService.findDessertById(dessertId)
                .orElseThrow(() -> new DessertNotFoundException("dessertWithID " + dessertId + " not found"));

        // creatingLunchOption
        LunchOption lunchOption = new LunchOption();
        lunchOption.setDish(dish);
        lunchOption.setDessert(dessert);

        return lunchOptionRepository.save(lunchOption);
    }
}
