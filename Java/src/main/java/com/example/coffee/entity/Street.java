package com.example.coffee.entity;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "streets", schema = "coffee", catalog = "coffee")
public class Street {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_street", nullable = false)
    private Long idStreet;

    @Column(name = "name", nullable = false, length = 45)
    private String name;

    @OneToMany(mappedBy = "street",cascade = CascadeType.MERGE)
    @ToString.Exclude
    private Collection<Establishment> establishments;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Street street = (Street) o;

        return Objects.equals(idStreet, street.idStreet);
    }

    @Override
    public int hashCode() {
        return 1874184581;
    }
}
