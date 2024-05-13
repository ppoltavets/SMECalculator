package com.example.smecalculator.service;

import com.example.smecalculator.entity.CashFlowEntity;
import com.example.smecalculator.entity.OperationalBudgetEntity;
import com.example.smecalculator.repository.OperationalBudgetRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@Service
public class OpBudgetService implements IOpBudgetService{

    final
    OperationalBudgetRepository repository;

    public OpBudgetService(OperationalBudgetRepository repository) {
        this.repository = repository;
    }
    @Override
    public Map<LocalDate, OperationalBudgetEntity> getOpBudget(String login) {
        Map<LocalDate, OperationalBudgetEntity> costs = new HashMap<>();
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
    public void saveOpBudget(OperationalBudgetEntity operationalBudget) {
        operationalBudget.setCalculate(operationalBudget.getSalesIncome() - (operationalBudget.getProductionCosts()) + operationalBudget.getBills() + operationalBudget.getServiceCosts() + operationalBudget.getLogistics() + operationalBudget.getAmortization() + operationalBudget.getReserves());
        repository.save(operationalBudget);
    }
}
