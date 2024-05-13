package com.example.smecalculator.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@SuperBuilder
@Table(schema = "public", name = "cash_flow")
public class CashFlowEntity {

    @Id
    private LocalDate date;

    @Column
    private String login;

    @Column
    private Double income;

    @Column
    private Double spendings;

    @Column(name = "credit_payments")
    private Double creditPayments;

    @Column
    private Double dividends;

    @Column
    private Double calculate;
}
