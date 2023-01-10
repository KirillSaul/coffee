package com.example.coffee.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "discount_foods", schema = "coffee", catalog = "coffee")
public class DiscountFood {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_discount_food", nullable = false)
    private Long idDiscountFood;

    @Column(name = "number", nullable = false)
    private Long number;

    @ManyToOne
    @JoinColumn(name = "id_food_type", nullable = false)
    private FoodType foodTypeByIdFoodType;
}
