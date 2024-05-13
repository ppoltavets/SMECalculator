package com.example.smecalculator.service;

import com.example.smecalculator.entity.CashFlowEntity;
import com.example.smecalculator.entity.OperationalBudgetEntity;

import java.time.LocalDate;
import java.util.Map;

public interface IOpBudgetService {

    public Map<LocalDate, OperationalBudgetEntity> getOpBudget(String login);
    public void saveOpBudget(OperationalBudgetEntity cashFlow);
}
