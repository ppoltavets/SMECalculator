package com.example.smecalculator.service;

import com.example.smecalculator.entity.CostsEntity;

import java.time.LocalDate;
import java.util.Map;

public interface ICostsService {

    public Map<LocalDate, CostsEntity> getCosts(String login);
    public void saveCosts(CostsEntity costs);
}
