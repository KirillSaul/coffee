package com.example.coffee.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;

@Data
@NoArgsConstructor
@Entity
@Table(name = "user_authorities", schema = "coffee", catalog = "coffee")
public class UserAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user_authority", nullable = false)
    private Long idUserAuthority;

    @Column(name = "name", nullable = false, length = 45)
    private String name;

    @OneToMany(mappedBy = "userAuthorityByIdUserAuthority")
    private Collection<User> userByIdUserAuthority;
}
