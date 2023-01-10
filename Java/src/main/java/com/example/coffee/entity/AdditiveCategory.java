package com.example.coffee.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Getter
@Setter
@ToString
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        AdditiveCategory that = (AdditiveCategory) o;

        return Objects.equals(idAdditiveCategory, that.idAdditiveCategory);
    }

    @Override
    public int hashCode() {
        return 249701001;
    }
}
