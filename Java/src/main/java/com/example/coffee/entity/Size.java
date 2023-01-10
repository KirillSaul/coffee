package com.example.coffee.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;

@Data
@NoArgsConstructor
@Entity
@Table(name = "sizes", schema = "coffee", catalog = "coffee")
public class Size {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_size", nullable = false)
    private Long idSize;

    @Column(name = "name", nullable = false, length = 45)
    private String name;

    @Column(name = "description", nullable = true, length = 45)
    private String description;

    @OneToMany(mappedBy = "sizeByIdSize")
    private Collection<Drink> drinkByIdSize;

    @OneToMany(mappedBy = "sizeByIdSize")
    private Collection<Food> foodByIdSize;

    @OneToMany(mappedBy = "sizeBySizesIdSize")
    private Collection<Price> priceByIdSize;
}
