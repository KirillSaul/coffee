package com.example.coffee.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Collection;

@Data
@NoArgsConstructor
@Entity
@Table(name = "additives", schema = "coffee", catalog = "coffee")
public class Additive {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_additive", nullable = false)
    private Long idAdditive;

    @Column(name = "name", nullable = false, length = 45)
    private String name;

    @Column(name = "price", nullable = false)
    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name = "id_additive_category", nullable = false)
    private AdditiveCategory additiveCategoriesByIdAdditiveCategory;

    @OneToMany(mappedBy = "additiveByIdAdditive")
    private Collection<Product> productByIdAdditive;
}
