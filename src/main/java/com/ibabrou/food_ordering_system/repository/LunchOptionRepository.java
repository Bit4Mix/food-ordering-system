package com.ibabrou.food_ordering_system.repository;

import com.ibabrou.food_ordering_system.entity.LunchOption;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LunchOptionRepository extends JpaRepository<LunchOption, Long> {
}
