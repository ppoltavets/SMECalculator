package com.example.smecalculator.service;

import com.example.smecalculator.entity.BalanceEntity;
import com.example.smecalculator.entity.CostsEntity;
import com.example.smecalculator.repository.BalanceRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@Service
public class BalanceService implements IBalanceService{

    final
    BalanceRepository repository;

    public BalanceService(BalanceRepository repository) {
        this.repository = repository;
    }
    @Override
    public Map<LocalDate, BalanceEntity> getBalance(String login) {
        Map<LocalDate, BalanceEntity> costs = new HashMap<>();
        var foundBalance = repository.findByLoginOrderByDate(login);
        for (int a = 0; a < foundBalance.size(); a++)
        {
            costs.put(foundBalance.get(a).getDate(), foundBalance.get(a));
        }
        return costs;
    }

    /*
Общий баланс = (Касса + Расчетный счет + Денежные резервы + Задолженность покупателей + Предоплата поставщику + Прочие оборотные активы) - Текущие обязательства
 */
    @Override
    public void saveBalance(BalanceEntity balance) {
        balance.setCalculate((balance.getFund() + balance.getBankAccount() + balance.getReserves() + balance.getClientDebt() + balance.getUpFrontPayment() + balance.getOtherAssets()) - balance.getRequiredPayments());
        repository.save(balance);
    }
}
