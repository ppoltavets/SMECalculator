package com.example.smecalculator.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity

@Data
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
@Table(schema = "public", name = "users")
public class RegistrationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gen1")
    private Integer id;

    @Column
    private String login;

    @Column
    private String password;

}

