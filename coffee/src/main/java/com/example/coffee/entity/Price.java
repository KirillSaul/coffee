package com.example.coffee.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Collection;

@Data
@NoArgsConstructor
@Entity
@Table(name = "prices", schema = "coffee", catalog = "coffee")
public class Price {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_price", nullable = false)
    private Long idPrice;

    @Column(name = "price", nullable = false)
    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name = "products_id_size", nullable = false)
    private Product productByProductIdSize;

    @ManyToOne
    @JoinColumn(name = "sizes_id_size", nullable = false)
    private Size sizeBySizesIdSize;

    @ManyToOne
    @JoinColumn(name = "id_user", nullable = false)
    private User usersByIdUser;

    @OneToMany(mappedBy = "priceByIdPrice")
    private Collection<TransactionsHistory> transactionsHistoryByIdPrice;

}
