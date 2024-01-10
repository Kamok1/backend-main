package com.ecobank.api.database.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "accounts_type")
public class AccountType {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column(unique = true)
    private String type;
}