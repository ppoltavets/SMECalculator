package com.example.smecalculator.service;

import com.example.smecalculator.entity.CostsEntity;
import com.example.smecalculator.entity.DateEntity;
import com.example.smecalculator.repository.CostsRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@Service
public class CostsService implements ICostsService {

    final
    CostsRepository repository;

    public CostsService(CostsRepository repository) {
        this.repository = repository;
    }


    @Override
    public Map<LocalDate, CostsEntity> getCosts(String login) {
        Map<LocalDate, CostsEntity> costs = new HashMap<>();
        var foundCosts = repository.findByLoginOrderByDate(login);
        for (int a = 0; a < foundCosts.size(); a++)
        {
            costs.put(foundCosts.get(a).getDate(), foundCosts.get(a));
        }
        return costs;
    }

    @Override
    public void saveCosts(CostsEntity costs) {
        costs.setSumm(costs.getSalary() + costs.getBonus() + costs.getSalaryTaxes() + costs.getRent() + costs.getAds() + costs.getTaxes() + costs.getPatent());
        repository.save(costs);
    }
}
