package com.ibabrou.food_ordering_system.repository;

import com.ibabrou.food_ordering_system.entity.Drink;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DrinkRepository extends JpaRepository<Drink, Long> {
}
