package com.example.coffee.entity;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@Builder
@Data
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
}
