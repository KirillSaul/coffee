package com.example.coffee.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "foods", schema = "coffee", catalog = "coffee")
public class Food {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_food_type", nullable = false)
    private Long idFoodType;

    @OneToOne
    @JoinColumn(name = "id_food_type", nullable = false)
    private FoodType foodTypeByIdFoodType;

    @ManyToOne
    @JoinColumn(name = "id_product_type", nullable = false)
    private ProductType productTypeByIdProductType;

    @ManyToOne
    @JoinColumn(name = "id_product", nullable = false)
    private Product productByIdProduct;

    @ManyToOne
    @JoinColumn(name = "id_size", nullable = false)
    private Size sizeByIdSize;
}
