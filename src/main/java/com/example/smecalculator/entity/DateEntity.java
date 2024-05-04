package com.example.smecalculator.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
public class DateEntity {

    @JsonProperty
    private Integer year;

    @JsonProperty
    private Integer month;
}
