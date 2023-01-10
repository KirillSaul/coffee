package com.example.coffee.entity;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Data
@RequiredArgsConstructor
@Entity
@Table(name = "additive_categories", schema = "coffee", catalog = "coffee")
public class AdditiveCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_additive_category", nullable = false)
    private Long idAdditiveCategory;
    @Column(name = "name", nullable = false, length = 45)
    private String name;
    @OneToMany(mappedBy = "additiveCategoriesByIdAdditiveCategory")
    @ToString.Exclude
    private Collection<Additive> additiveByIdAdditiveCategory;
}
