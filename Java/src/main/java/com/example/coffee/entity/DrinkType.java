package com.example.coffee.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Collection;

@Data
@NoArgsConstructor
@Entity
@Table(name = "drink_types", schema = "coffee", catalog = "coffee")
public class DrinkType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_drink_type", nullable = false)
    private Long idDrinkType;

    @Column(name = "name", nullable = false)
    private BigDecimal name;

    @OneToMany(mappedBy = "drinkTypeByIdDrinkType")
    private Collection<DiscountDrink> discountDrinkByIdDrinkType;

    @OneToOne(mappedBy = "drinkTypeByIdDrinkType")
    private Drink drinkByIdDrinkType;
}
