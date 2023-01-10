package com.example.coffee.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "drinks", schema = "coffee", catalog = "coffee")
public class Drink {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_drink_type", nullable = false)
    private Long idDrinkType;

    @OneToOne
    @JoinColumn(name = "id_drink_type", nullable = false)
    private DrinkType drinkTypeByIdDrinkType;

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
