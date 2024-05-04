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
@SuperBuilder
@NoArgsConstructor
@Table(schema = "public", name = "costs")
public class CostsEntity {

    @Column
    private String login;

    @Column
    private Double salary;

    @Column
    private Double bonus;

    @Column(name = "salary_taxes")
    private Double salaryTaxes;

    @Column
    private Double rent;

    @Column
    private Double ads;

    @Column
    private Double taxes;

    @Column
    private Double patent;

    @Id
    @Column
    private LocalDate date;

    @Column
    private Double summ;

}
