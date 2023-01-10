package com.example.coffee.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;

@Data
@NoArgsConstructor
@Entity
@Table(name = "product_types", schema = "coffee", catalog = "coffee")
public class ProductType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_product_type", nullable = false)
    private Long idProductType;

    @Column(name = "name", nullable = false, length = 45)
    private String name;

    @OneToMany(mappedBy = "productTypeByIdProductType")
    private Collection<Drink> drinkByIdProductType;

    @OneToMany(mappedBy = "productTypeByIdProductType")
    private Collection<Food> foodByIdProductType;
}
