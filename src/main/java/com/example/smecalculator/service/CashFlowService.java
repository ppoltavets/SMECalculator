package com.example.smecalculator.service;

import com.example.smecalculator.entity.BalanceEntity;
import com.example.smecalculator.entity.CashFlowEntity;
import com.example.smecalculator.repository.BalanceRepository;
import com.example.smecalculator.repository.CashFlowRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@Service
public class CashFlowService implements ICashFlowService{

    final
    CashFlowRepository repository;

    public CashFlowService(CashFlowRepository repository) {
        this.repository = repository;
    }
    @Override
    public Map<LocalDate, CashFlowEntity> getCashFlow(String login) {
        Map<LocalDate, CashFlowEntity> costs = new HashMap<>();
        var foundBalance = repository.findByLoginOrderByDate(login);
        for (int a = 0; a < foundBalance.size(); a++)
        {
            costs.put(foundBalance.get(a).getDate(), foundBalance.get(a));
        }
        return costs;
    }

    /*
Чистое движение средств = Приходы - (Расходы + Кредитные платежи + Дивиденды) */
    @Override
    public void saveCashFlow(CashFlowEntity cashFlow) {
        cashFlow.setCalculate(cashFlow.getIncome() - (cashFlow.getSpendings() + cashFlow.getCreditPayments() + cashFlow.getDividends()));
        repository.save(cashFlow);
    }
}
