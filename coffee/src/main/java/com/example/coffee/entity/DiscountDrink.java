package com.example.coffee.entity;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@Data
@NoArgsConstructor
@Entity
@Table(name = "discount_drinks", schema = "coffee", catalog = "coffee")
public class DiscountDrink {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_discount_drink", nullable = false)
    private Long idDiscountDrink;

    @Column(name = "number", nullable = false)
    private Integer number;

    @ManyToOne
    @JoinColumn(name = "id_drink_type", nullable = false)
    private DrinkType drinkTypeByIdDrinkType;
}
