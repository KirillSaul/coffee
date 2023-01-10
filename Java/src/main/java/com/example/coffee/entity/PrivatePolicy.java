package com.example.coffee.entity;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "private_policies", schema = "coffee", catalog = "coffee")
public class PrivatePolicy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_private_policy", nullable = false)
    private Long idPrivatePolicy;

    @Column(name = "description", nullable = false, length = -1, columnDefinition = "text")
    private String description;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        PrivatePolicy that = (PrivatePolicy) o;

        return Objects.equals(idPrivatePolicy, that.idPrivatePolicy);
    }

    @Override
    public int hashCode() {
        return 1584937793;
    }
}
