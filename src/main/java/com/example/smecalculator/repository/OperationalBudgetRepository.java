package com.example.smecalculator.repository;

import com.example.smecalculator.entity.CashFlowEntity;
import com.example.smecalculator.entity.OperationalBudgetEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OperationalBudgetRepository extends JpaRepository<OperationalBudgetEntity, String> {

    List<OperationalBudgetEntity> findByLoginOrderByDate(String login);
}
