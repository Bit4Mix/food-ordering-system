package com.ibabrou.food_ordering_system.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Entity
@Table(name = "order_lunch")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "lunch_option_id")
    private LunchOption lunchOption;

    @ManyToOne
    @JoinColumn(name = "drink_option_id")
    private DrinkOption drinkOption;


    private BigDecimal totalPrice;

    public BigDecimal calculateTotalPrice() {
        BigDecimal total = BigDecimal.ZERO;

        if (lunchOption != null) {
            BigDecimal lunchPrice = lunchOption.getDish().getPrice().add(lunchOption.getDessert().getPrice());
            total = total.add(lunchPrice);
        }

        if (drinkOption != null) {
            total = total.add(drinkOption.getDrink().getPrice());
        }

        return total.setScale(2, RoundingMode.HALF_UP);
    }
}
