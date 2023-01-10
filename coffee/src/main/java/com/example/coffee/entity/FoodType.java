package com.example.coffee.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;

@Data
@NoArgsConstructor
@Entity
@Table(name = "food_types", schema = "coffee", catalog = "coffee")
public class FoodType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_food_type", nullable = false)
    private Long idFoodType;

    @Column(name = "name", nullable = false, length = 45)
    private String name;

    @OneToMany(mappedBy = "foodTypeByIdFoodType")
    private Collection<DiscountFood> discountFoodByIdFoodType;

    @OneToOne(mappedBy = "foodTypeByIdFoodType")
    private Food foodByIdFoodType;
}
