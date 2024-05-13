package com.example.smecalculator.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
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
    @Builder.Default
    private Double salary = 0.0;

    @Column
    @Builder.Default

    private Double bonus = 0.0;

    @Column(name = "salary_taxes")
    @Builder.Default
    private Double salaryTaxes = 0.0;

    @Column
    @Builder.Default
    private Double rent = 0.0;

    @Column
    @Builder.Default
    private Double ads = 0.0;

    @Column
    @Builder.Default
    private Double taxes = 0.0;

    @Column
    @Builder.Default
    private Double patent = 0.0;

    @Id
    @Column
    private LocalDate date;

    @Column
    private Double summ;

}
