package com.example.coffee.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;

@Data
@NoArgsConstructor
@Entity
@Table(name = "products", schema = "coffee", catalog = "coffee")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_product", nullable = false)
    private Long idProduct;

    @Column(name = "name", nullable = false, length = 45)
    private String name;

    @Column(name = "description", nullable = false, length = 255)
    private String description;

    @Column(name = "path_image", nullable = false, length = 255)
    private String pathImage;

    @Column(name = "is_reward", nullable = false)
    private Boolean isReward;

    @OneToMany(mappedBy = "productByIdProduct")
    private Collection<Drink> drinkByIdProduct;

    @OneToMany(mappedBy = "productByIdProduct")
    private Collection<Food> foodByIdProduct;

    @OneToMany(mappedBy = "productByProductIdSize")
    private Collection<Price> priceByIdProduct;

    @ManyToOne
    @JoinColumn(name = "id_additive", nullable = false)
    private Additive additiveByIdAdditive;
}
