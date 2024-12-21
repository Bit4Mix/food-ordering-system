package com.ibabrou.food_ordering_system.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "drink_option")
@Getter
@Setter
@NoArgsConstructor
public class DrinkOption {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "drink_id")
    private Drink drink;

    private boolean withIce;
    private boolean withLemon;

}
