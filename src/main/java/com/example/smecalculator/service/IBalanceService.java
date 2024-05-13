package com.example.smecalculator.service;

import com.example.smecalculator.entity.BalanceEntity;

import java.time.LocalDate;
import java.util.Map;

public interface IBalanceService {

    public Map<LocalDate, BalanceEntity> getBalance(String login);
    public void saveBalance(BalanceEntity balance);
}
