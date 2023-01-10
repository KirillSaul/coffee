package com.example.coffee.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;

@Data
@NoArgsConstructor
@Entity
@Table(name = "transactions_histories", schema = "coffee", catalog = "coffee")
public class TransactionsHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_transaction_history", nullable = false)
    private Long idTransactionHistory;

    @Column(name = "date_of_order", nullable = false)
    private Date dateOfOrder;

    @ManyToOne
    @JoinColumn(name = "id_price", nullable = false)
    private Price priceByIdPrice;
}
