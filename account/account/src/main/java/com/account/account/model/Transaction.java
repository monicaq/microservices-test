package com.account.account.model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;

@Entity
@Data
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date date;
    private String transactionType;
    private Double value;
    private Double cash;

    @ManyToOne
    @JoinColumn(name = "id_account", nullable = false)
    private Account account;
}
