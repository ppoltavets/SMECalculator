package com.example.smecalculator.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@Entity

@Data
@NoArgsConstructor
@SuperBuilder
@Table(schema = "public", name = "users")
public class RegistrationEntity {

    @Id
    @Column
    private String login;

    @Column
    private String password;

    @Column
    private String name;

    @Column
    private String surname;

    @Column(name = "company_name")
    private String companyName;

    @Column
    private Integer yield;

}

