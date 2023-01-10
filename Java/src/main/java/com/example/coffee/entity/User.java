package com.example.coffee.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;

@Data
@NoArgsConstructor
@Entity
@Table(name = "users", schema = "coffee", catalog = "coffee")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user", nullable = false)
    private Long idUser;

    @Column(name = "name", nullable = false, length = 45)
    private String name;

    @Column(name = "login", nullable = false, length = 45)
    private String login;

    @Column(name = "password", nullable = false, length = 45)
    private String password;

    @Column(name = "enabled", nullable = false)
    private Boolean enabled;

    @OneToMany(mappedBy = "usersByIdUser")
    private Collection<Price> priceByIdUser;

    @ManyToOne
    @JoinColumn(name = "id_user_authorities", nullable = false)
    private UserAuthority userAuthorityByIdUserAuthority;
}
