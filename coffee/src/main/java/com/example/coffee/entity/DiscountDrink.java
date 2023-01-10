package com.example.coffee.entity;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Setter
@ToString
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        DiscountDrink that = (DiscountDrink) o;

        return Objects.equals(idDiscountDrink, that.idDiscountDrink);
    }

    @Override
    public int hashCode() {
        return 722083191;
    }
}
