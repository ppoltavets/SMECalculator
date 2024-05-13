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

/*
Общий баланс = (Касса + Расчетный счет + Денежные резервы + Задолженность покупателей + Предоплата поставщику + Прочие оборотные активы) - Текущие обязательства
 */
@Data
@Entity
@NoArgsConstructor
@SuperBuilder
@Table(schema = "public", name = "balance")
public class BalanceEntity {

    @Column
    private String login;

    @Column
    @Builder.Default
    private Double fund = 0.0;

    @Column(name = "bank_account")
    @Builder.Default
    private Double bankAccount = 0.0;

    @Column
    @Builder.Default
    private Double reserves = 0.0;

    @Column(name = "client_debt")
    @Builder.Default
    private Double clientDebt = 0.0;

    @Column(name = "up_front_payment")
    @Builder.Default
    private Double upFrontPayment = 0.0;

    @Column(name = "other_assets")
    @Builder.Default
    private Double otherAssets = 0.0;

    @Column(name = "required_payments")
    @Builder.Default
    private Double requiredPayments = 0.0;

    @Id
    @Column
    private LocalDate date;

    @Column
    private Double calculate;
}
