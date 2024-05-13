package com.example.smecalculator.service;

import com.example.smecalculator.entity.BalanceEntity;
import com.example.smecalculator.entity.CashFlowEntity;
import com.example.smecalculator.entity.OperationalBudgetEntity;

import java.time.LocalDate;
import java.util.Map;

public interface ICashFlowService {

    public Map<LocalDate, CashFlowEntity> getCashFlow(String login);

    public void saveCashFlow(CashFlowEntity cashFlow);
}
