package com.example.smecalculator.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Data
@NoArgsConstructor
@SuperBuilder
@Table(name = "operational_budget", schema = "public", catalog = "postgres")
public class OperationalBudgetEntity {
    @Column(name = "login")
    private String login;

    @Column(name = "sales_income")
    private Double salesIncome;

    @Column(name = "production_costs")
    private Double productionCosts;

    @Column(name = "bills")
    private Double bills;

    @Column(name = "service_costs")
    private Double serviceCosts;

    @Column(name = "logistics")
    private Double logistics;

    @Column(name = "amortization")
    private Double amortization;

    @Column(name = "reserves")
    private Double reserves;

    @Column(name = "calculate")
    private Double calculate;

    @Id
    @Column(name = "date")
    private LocalDate date;
}
