package com.ibabrou.food_ordering_system.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "dessert")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Dessert {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    private BigDecimal price;
}
