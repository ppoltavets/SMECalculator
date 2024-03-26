package com.example.smecalculator.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Data
@SuperBuilder
@NoArgsConstructor
@Table(schema = "public", name = "tokens")
public class TokensEntity {

    @Id
    @Column(name = "username")
    private String username;

    @Column(name = "token")
    private String token;
}
