package com.ibabrou.food_ordering_system.service;

import com.ibabrou.food_ordering_system.entity.Drink;
import com.ibabrou.food_ordering_system.entity.DrinkOption;
import com.ibabrou.food_ordering_system.exception.DrinkNotFoundException;
import com.ibabrou.food_ordering_system.repository.DrinkOptionRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DrinkOptionService {

    @Autowired
    private DrinkOptionRepository drinkOptionRepository;
    @Autowired
    private DrinkService drinkService;

    public Optional<DrinkOption> getDrinkOptionById(long id) {
        return drinkOptionRepository.findById(id);
    }

    @Transactional
    public DrinkOption createDrinkOption(Long drinkId, boolean withIce, boolean withLemon) throws DrinkNotFoundException {
        // checkingTheAvailabilityOfDrink
        Drink drink = drinkService.findDrinkById(drinkId)
                .orElseThrow(() -> new DrinkNotFoundException("drinkWithID " + drinkId + " not found"));

        // creatingDrinkOption
        DrinkOption drinkOption = new DrinkOption();
        drinkOption.setDrink(drink);
        drinkOption.setWithIce(withIce);
        drinkOption.setWithLemon(withLemon);

        return drinkOptionRepository.save(drinkOption);
    }
}
